package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.TraceIdHolder;
import com.ryuqq.setof.enums.core.EntityType;
import com.ryuqq.setof.enums.core.RequestType;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.enums.core.SyncStep;
import com.ryuqq.setof.support.external.core.ExternalMallPreProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallSyncResponse;
import com.ryuqq.setof.support.external.core.SyncResultSummary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExternalMallSyncResponseContextAdapter {

    public ExternalMallSyncResponseContext toDomains(long siteId, SyncResultSummary syncResultSummary){
        return new ExternalMallSyncResponseContext(
                toExternalProductUpdateCommand(siteId, syncResultSummary),
                toExternalRequestCommands(siteId, syncResultSummary),
                toExternalProductImageCommands(syncResultSummary.successResponses())
        );
    }

    public List<ExternalProductUpdateCommand> toExternalProductUpdateCommand(long siteId, SyncResultSummary syncResultSummary) {
        List<ExternalProductUpdateCommand> externalProductUpdateCommands = new ArrayList<>();
        
        List<ExternalProductUpdateCommand> successRequests = syncResultSummary.successResponses().stream()
                .map(e -> new ExternalProductUpdateCommand(
                        siteId,
                        e.productGroupId(),
                        e.externalProductId(),
                        e.productName(),
                        e.regularPrice(),
                        e.currentPrice(),
                        SyncStatus.APPROVED,
                        e.soldOutYn(),
                        e.displayYn()
                ))
                .toList();

        List<ExternalProductUpdateCommand> failRequests = syncResultSummary.failureResponses().stream()
                .map(f -> new ExternalProductUpdateCommand(
                        siteId,
                        f.productGroupId(),
                        "",
                        "",
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        SyncStatus.FAILED,
                        false,
                        true
                ))
                .toList();

        externalProductUpdateCommands.addAll(successRequests);
        externalProductUpdateCommands.addAll(failRequests);

        return externalProductUpdateCommands;
    }


    public List<ExternalRequestCommand> toExternalRequestCommands(long siteId, SyncResultSummary syncResultSummary){
        List<ExternalRequestCommand> externalRequestCommands = new ArrayList<>();

        List<ExternalRequestCommand> successRequests = syncResultSummary.successResponses().stream()
                .map(e -> new ExternalRequestCommand(
                        TraceIdHolder.getTraceId(),
                        RequestType.PRODUCT_REGISTER,
                        siteId,
                        EntityType.PRODUCT,
                        e.productGroupId(),
                        SyncStatus.APPROVED,
                        e.requestBody()
                ))
                .toList();

        List<ExternalRequestCommand> failRequests = syncResultSummary.failureResponses().stream()
                .map(f -> new ExternalRequestCommand(
                        TraceIdHolder.getTraceId(),
                        RequestType.PRODUCT_REGISTER,
                        siteId,
                        EntityType.PRODUCT,
                        f.productGroupId(),
                        SyncStatus.FAILED,
                        f.requestBody()
                ))
                .toList();

        externalRequestCommands.addAll(successRequests);
        externalRequestCommands.addAll(failRequests);

        return externalRequestCommands;
    }

    public List<ExternalProductImageCommand> toExternalProductImageCommands(List<ExternalMallSyncResponse> successResponses) {
        return successResponses.stream()
                .flatMap(response ->
                        response.imageRequestResults().stream()
                                .map(imageResult -> new ExternalProductImageCommand(
                                        response.productGroupId(),
                                        response.externalProductId(),
                                        imageResult.order(),
                                        imageResult.imageUrl(),
                                        imageResult.originUrl()
                                ))
                )
                .toList();
    }



}
