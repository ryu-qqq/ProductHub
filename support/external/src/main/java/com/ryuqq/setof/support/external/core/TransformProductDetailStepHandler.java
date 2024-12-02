package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;
import com.ryuqq.setof.support.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class TransformProductDetailStepHandler implements SyncStepHandler {

    private final ExternalMallContextMapper externalMallContextMapper;
    private final ExternalMallContextBuilder externalMallContextBuilder;

    public TransformProductDetailStepHandler(ExternalMallContextMapper externalMallContextMapper, ExternalMallContextBuilder externalMallContextBuilder) {
        this.externalMallContextMapper = externalMallContextMapper;
        this.externalMallContextBuilder = externalMallContextBuilder;
    }


    @Override
    public SyncStepResult execute(ExternalMallPreProductContext context) {
        ExternalMallProductContext.Builder builder = externalMallContextBuilder.getBuilder();
        ExternalMallProductDetailContext externalMallProductDetailContext = externalMallContextMapper.generateProductDetailContext(context);
        builder.withDetailContext(context.getProductGroupId(), context.getSetOfProductGroupId(), externalMallProductDetailContext);
        return SyncStepResult.success(SyncStep.TRANSFORM_PRODUCT_DETAIL, builder);

    }

    @Override
    public SyncStep getStep() {
        return SyncStep.TRANSFORM_PRODUCT_DETAIL;
    }


}
