package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.TraceIdHolder;
import com.ryuqq.setof.enums.core.EntityType;
import com.ryuqq.setof.enums.core.RequestType;
import com.ryuqq.setof.enums.core.SyncStatus;

import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.utils.JsonUtils;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExternalMallIntegrationManager {

    private final ProductPreExternalSyncContextFinder productPreExternalSyncContextFinder;
    private final ExternalMallSyncBatchContextAdapter externalMallSyncBatchContextAdapter;
    private final ExternalMallSyncService externalMallSyncService;
    private final ExternalProductCommandService externalProductCommandService;
    private final ExternalRequestCommandService externalRequestCommandService;
    private final ExternalProductImageCommandService externalProductImageCommandService;

    public ExternalMallIntegrationManager(ProductPreExternalSyncContextFinder productPreExternalSyncContextFinder, ExternalMallSyncBatchContextAdapter externalMallSyncBatchContextAdapter, ExternalMallSyncService externalMallSyncService, ExternalProductCommandService externalProductCommandService, ExternalRequestCommandService externalRequestCommandService, ExternalProductImageCommandService externalProductImageCommandService) {
        this.productPreExternalSyncContextFinder = productPreExternalSyncContextFinder;
        this.externalMallSyncBatchContextAdapter = externalMallSyncBatchContextAdapter;
        this.externalMallSyncService = externalMallSyncService;
        this.externalProductCommandService = externalProductCommandService;
        this.externalRequestCommandService = externalRequestCommandService;
        this.externalProductImageCommandService = externalProductImageCommandService;
    }

    @Transactional
    public Integer integration(long siteId, long sellerId, SyncStatus status, int pageSize){
        ExternalSyncBatchContext externalSyncBatchContext = productPreExternalSyncContextFinder.fetchBySiteId(siteId, sellerId, status, pageSize);

        List<Long> failedProductGroupIds = new ArrayList<>();
        List<ExternalMallRegistrationResult> successResult = new ArrayList<>();
        List<ExternalMallRegistrationResult> failResult = new ArrayList<>();
        List<ExternalRequestCommand> externalRequestCommands = new ArrayList<>();
        List<ExternalProductImageCommand> externalProductImageCommands = new ArrayList<>();


        List<ExternalMallRegistrationResult> syncResults = externalMallSyncService.sync(
                externalMallSyncBatchContextAdapter.toExternalMallContexts(externalSyncBatchContext)
        );

        for(ExternalMallRegistrationResult result : syncResults){
            boolean success = result.getUploadStatus().success();
            if(success) successResult.add(result);
            else failResult.add(result);

            externalRequestCommands.add(toRequestCommand(result));
            externalProductImageCommands.addAll(toExternalProductImageCommand(result));
        }

        List<ExternalMallUpdateCommand> updateCommands = successResult.stream()
                .filter(e -> e.getUploadStatus().success())
                .map(ExternalMallRegistrationResult::getExternalMallUpdateResult)
                .map(ExternalMallUpdateCommand::of)
                .toList();

        externalProductCommandService.updateByExternalMallUpdateCommand(updateCommands);
        externalRequestCommandService.saveAllExternalRequest(externalRequestCommands);
        externalProductImageCommandService.saveAllExternalProductImages(externalProductImageCommands);

        if(!externalSyncBatchContext.failProducts().isEmpty()){
            List<Long> productGroupIds = externalSyncBatchContext.failProducts().stream().map(ExternalProduct::productGroupId).toList();
            failedProductGroupIds.addAll(productGroupIds);
        }

        if(!failResult.isEmpty()){
            List<Long> syncFailedProductGroupIds = failResult.stream().map(e -> e.getExternalMallUpdateResult().productGroupId()).toList();
            failedProductGroupIds.addAll(syncFailedProductGroupIds);
        }

        if(!failedProductGroupIds.isEmpty()){
            externalProductCommandService.updateStatusByProductGroupId(failedProductGroupIds, siteId, SyncStatus.FAILED);
        }

        return syncResults.size();
    }


    private List<ExternalProductImageCommand> toExternalProductImageCommand(ExternalMallRegistrationResult result){
        return result.getExternalMallUpdateResult().externalMallImageResults().stream()
                .map(ExternalProductImageCommand::of)
                .toList();
    }

    private ExternalRequestCommand toRequestCommand(ExternalMallRegistrationResult result) {
        ExternalMallRegistrationRequest registrationRequest = result.getRegistrationRequest();
        ExternalMallUpdateResult externalMallUpdateResult = result.getExternalMallUpdateResult();
        boolean success = result.getUploadStatus().success();

        String jsonBody = success
                ? JsonUtils.toJson(registrationRequest.externalMallProductPayload())
                : String.format("{\"failedReason\": \"%s\"}", result.getUploadStatus().errorDetails());

        return new ExternalRequestCommand(
                TraceIdHolder.getTraceId(),
                RequestType.PRODUCT_REGISTER,
                result.getExternalMallUpdateResult().siteId(),
                EntityType.PRODUCT,
                externalMallUpdateResult.productGroupId(),
                result.getExternalMallUpdateResult().status(),
                jsonBody
        );

    }

}
