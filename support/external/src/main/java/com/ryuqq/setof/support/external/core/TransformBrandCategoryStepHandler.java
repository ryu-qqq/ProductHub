package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;
import org.springframework.stereotype.Component;

@Component
public class TransformBrandCategoryStepHandler implements SyncStepHandler {

    private final ExternalMallContextMapper externalMallContextMapper;
    private final ExternalMallContextBuilder externalMallContextBuilder;

    public TransformBrandCategoryStepHandler(ExternalMallContextMapper externalMallContextMapper, ExternalMallContextBuilder externalMallContextBuilder) {
        this.externalMallContextMapper = externalMallContextMapper;
        this.externalMallContextBuilder = externalMallContextBuilder;
    }

    @Override
    public SyncStepResult execute(ExternalMallPreProductContext context) {
        ExternalMallProductContext.Builder builder = externalMallContextBuilder.getBuilder();
        ExternalMallCategoryAndBrandContext externalMallCategoryAndBrandContext = externalMallContextMapper.generateCategoryAndBrand(context);
        builder.withCategoryAndBrand(externalMallCategoryAndBrandContext);
        return SyncStepResult.success(SyncStep.TRANSFORM_BRAND_CATEGORY, builder);

    }

    @Override
    public SyncStep getStep() {
        return SyncStep.TRANSFORM_BRAND_CATEGORY;
    }


}
