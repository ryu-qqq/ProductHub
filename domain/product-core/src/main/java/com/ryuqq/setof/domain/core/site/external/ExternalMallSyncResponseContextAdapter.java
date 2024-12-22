package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.TraceIdHolder;
import com.ryuqq.setof.enums.core.EntityType;
import com.ryuqq.setof.enums.core.RequestType;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.db.core.site.external.ExternalProductUpdateCommand;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import com.ryuqq.setof.support.external.core.ExternalMallSyncedOption;
import com.ryuqq.setof.support.external.core.SyncResultSummary;
import com.ryuqq.setof.support.external.core.SyncStepResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ExternalMallSyncResponseContextAdapter {

    private final static int SUCCESS_VALUE = 200;
    private final static String SUCCESS_MESSAGE = "SUCCESS";

    public ExternalMallSyncResponseContext toDomains(long siteId, SyncResultSummary syncResultSummary, List<ProductPreExternalSyncContext> syncData){
        Map<Long, ProductPreExternalSyncContext> productPreExternalSyncContextMap = mapToProductPreExternalSyncContext(syncData);

        return new ExternalMallSyncResponseContext(
                toExternalProductGroupUpdateCommands(siteId, syncResultSummary),
                toExternalProductUpdateCommands(syncResultSummary, productPreExternalSyncContextMap),
                toExternalRequestCommands(siteId, syncResultSummary),
                toExternalProductImageCommands(siteId, syncResultSummary)
        );
    }

    private List<ExternalProductGroupUpdateCommand> toExternalProductGroupUpdateCommands(long siteId, SyncResultSummary syncResultSummary) {
        return mergeCommands(
                syncResultSummary.successResponses().stream()
                        .map(e -> mapToProductGroupUpdateCommand(siteId, e.getExternalMallProductContextBuilder().build(), e.getStatus()))
                        .toList(),
                syncResultSummary.failureResponses().stream()
                        .map(f -> mapToProductGroupUpdateCommand(siteId, f.getExternalMallProductContextBuilder().build(), f.getStatus()))
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

    private List<ExternalProductUpdateCommand> toExternalProductUpdateCommands(SyncResultSummary syncResultSummary, Map<Long, ProductPreExternalSyncContext> productPreExternalSyncContextMap) {
        return mergeCommands(
                syncResultSummary.successResponses().stream()
                        .flatMap(e -> mapToProductUpdateCommand(
                                e.getExternalMallProductContextBuilder().build(),
                                e.getStatus(),
                                productPreExternalSyncContextMap
                        ).stream())
                        .toList(),
                syncResultSummary.failureResponses().stream()
                        .flatMap(f -> mapToProductUpdateCommand(
                                f.getExternalMallProductContextBuilder().build(),
                                f.getStatus(),
                                productPreExternalSyncContextMap
                        ).stream())
                        .toList()
        );
    }

    private List<ExternalProductUpdateCommand> mapToProductUpdateCommand(ExternalMallProductContext context, SyncStatus status, Map<Long, ProductPreExternalSyncContext> productPreExternalSyncContextMap) {

        ProductPreExternalSyncContext productPreExternalSyncContext =
                productPreExternalSyncContextMap.get(context.getProductGroupId());

        if (productPreExternalSyncContext == null || !(status.isApproved() || status.isReview())) {
            return List.of();
        }


        Map<String, ExternalMallSyncedOption> optionValueMap = context.getExternalMallSyncedOptions().stream()
                .collect(Collectors.toMap(
                        ExternalMallSyncedOption::optionValue,
                        Function.identity(),
                        (existing, replacement) -> existing
                ));



        return productPreExternalSyncContext.productGroupContext().getProducts().stream()
                .map(product -> {
                    ExternalMallSyncedOption syncedOption = optionValueMap.get(product.getOption());
                    if (syncedOption != null) {
                        return new ExternalProductUpdateCommand(
                                context.getExternalProductGroupId(),
                                syncedOption.externalProductId(),
                                syncedOption.optionValue(),
                                product.getQuantity(),
                                product.getAdditionalPrice().getAmount(),
                                product.isSoldOutYn(),
                                product.isDisplayYn()
                        );
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private ExternalProductGroupUpdateCommand mapToProductGroupUpdateCommand(long siteId, ExternalMallProductContext context, SyncStatus status) {

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
                context.getNameContext().name(),
                context.getPriceContext().regularPrice(),
                context.getPriceContext().currentPrice(),
                status,
                false,
                context.getProductStatusContext().soldOutYn(),
                context.getProductStatusContext().displayYn()
        );
    }

    private ExternalRequestCommand mapToRequestCommand(long siteId, SyncStepResult response, int statusCode, String message) {
        ExternalMallProductContext context = response.getExternalMallProductContextBuilder().build();
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


    private Map<Long, ProductPreExternalSyncContext> mapToProductPreExternalSyncContext(List<ProductPreExternalSyncContext> syncData){
        return syncData.stream()
                .collect(
                        Collectors.toMap(ProductPreExternalSyncContext::getProductGroupId, Function.identity(),
                                (v1, v2) -> v1)
                );
    }

}
