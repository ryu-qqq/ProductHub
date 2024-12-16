package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.TraceIdHolder;
import com.ryuqq.setof.enums.core.EntityType;
import com.ryuqq.setof.enums.core.RequestType;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import com.ryuqq.setof.support.external.core.SyncResultSummary;
import com.ryuqq.setof.support.external.core.SyncStepResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExternalMallSyncResponseContextAdapter {

    private final static int SUCCESS_VALUE = 200;
    private final static String SUCCESS_MESSAGE = "SUCCESS";

    public ExternalMallSyncResponseContext toDomains(long siteId, SyncResultSummary syncResultSummary){
        return new ExternalMallSyncResponseContext(
                toExternalProductUpdateCommands(siteId, syncResultSummary),
                toExternalRequestCommands(siteId, syncResultSummary),
                toExternalProductImageCommands(siteId, syncResultSummary)
        );
    }

    private List<ExternalProductGroupUpdateCommand> toExternalProductUpdateCommands(long siteId, SyncResultSummary syncResultSummary) {
        return mergeCommands(
                syncResultSummary.successResponses().stream()
                        .map(e -> mapToProductUpdateCommand(siteId, e, e.getStatus()))
                        .toList(),
                syncResultSummary.failureResponses().stream()
                        .map(f -> mapToProductUpdateCommand(siteId, f, f.getStatus()))
                        .toList()
        );
    }

    private List<ExternalRequestCommand> toExternalRequestCommands(long siteId, SyncResultSummary syncResultSummary) {
        return mergeCommands(
                syncResultSummary.successResponses().stream()
                        .map(e -> mapToRequestCommand(siteId, e, SUCCESS_VALUE, SUCCESS_MESSAGE))
                        .toList(),
                syncResultSummary.failureResponses().stream()
                        .map(f -> mapToRequestCommand(siteId, f, f.getErrorCode(), f.getErrorMessage()))
                        .toList()
        );
    }

    private ExternalProductGroupUpdateCommand mapToProductUpdateCommand(long siteId, SyncStepResult response, SyncStatus status) {
        ExternalMallProductContext context = response.getExternalMallProductContext();

        if(status.isApproved() || status.isReview()){
            return new ExternalProductGroupUpdateCommand(
                    siteId,
                    context.getProductGroupId(),
                    context.getExternalProductGroupId(),
                    context.getNameContext().name() ,
                    context.getPriceContext().regularPrice(),
                    context.getPriceContext().currentPrice(),
                    status,
                    false,
                    context.getProductStatusContext().soldOutYn(),
                    context.getProductStatusContext().displayYn()
            );
        }

        return new ExternalProductGroupUpdateCommand(
                siteId,
                context.getProductGroupId(),
                context.getExternalProductGroupId(),
                "",
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                status,
                false,
                false,
                true
        );
    }

    private ExternalRequestCommand mapToRequestCommand(long siteId, SyncStepResult response, int statusCode, String message) {
        ExternalMallProductContext context = response.getExternalMallProductContext();
        return new ExternalRequestCommand(
                TraceIdHolder.getTraceId(),
                RequestType.PRODUCT_REGISTER,
                siteId,
                EntityType.PRODUCT,
                response.getStatus(),
                context.getProductGroupId(),
                statusCode,
                message,
                response.getRequestBody() == null || response.getRequestBody().isBlank() ? "{}" : response.getRequestBody()
        );
    }

    private List<ExternalProductImageCommand> toExternalProductImageCommands(long siteId, SyncResultSummary syncResultSummary) {
        return syncResultSummary.successResponses().stream()
                .flatMap(response -> mapToImageCommands(response.getExternalMallProductContext(), siteId).stream())
                .toList();
    }

    private List<ExternalProductImageCommand> mapToImageCommands(ExternalMallProductContext context, long siteId) {
        return context.getImageGroupContext().externalMallImages().stream()
                .map(image -> new ExternalProductImageCommand(
                        context.getProductGroupId(),
                        siteId,
                        context.getExternalProductGroupId(),
                        image.order(),
                        image.imageUrl(),
                        image.originUrl()
                ))
                .toList();
    }

    private <T> List<T> mergeCommands(List<T> successCommands, List<T> failureCommands) {
        List<T> merged = new ArrayList<>(successCommands);
        merged.addAll(failureCommands);
        return merged;
    }

}
