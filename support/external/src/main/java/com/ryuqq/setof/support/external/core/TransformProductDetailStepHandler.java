package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;
import org.springframework.stereotype.Component;

@Component
public class TransformProductDetailStepHandler implements SyncStepHandler {

    private final ExternalMallContextMapperProvider externalMallContextMapperProvider;

    public TransformProductDetailStepHandler(ExternalMallContextMapperProvider externalMallContextMapperProvider) {
        this.externalMallContextMapperProvider = externalMallContextMapperProvider;
    }

    @Override
    public SyncStepResult execute(ExternalMallPreProductContext context, ExternalMallProductContext.Builder builder) {
        ExternalMallContextMapper externalMallContextMapper = externalMallContextMapperProvider.get(context.siteName());
        ExternalMallProductContext.Builder externalMallProductContextBuilder = externalMallContextMapper.toExternalMallProductContextBuilder(context);
        return SyncStepResult.success(SyncStep.TRANSFORM_PRODUCT_DETAIL, externalMallProductContextBuilder, "");
    }

    @Override
    public SyncStep getStep() {
        return SyncStep.TRANSFORM_PRODUCT_DETAIL;
    }


}
