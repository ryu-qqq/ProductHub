package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;
import org.springframework.stereotype.Component;

@Component
public class TransformImageStepHandler implements SyncStepHandler {
    private final ExternalMallContextMapper externalMallContextMapper;
    private final ExternalMallContextBuilder externalMallContextBuilder;

    public TransformImageStepHandler(ExternalMallContextMapper externalMallContextMapper, ExternalMallContextBuilder externalMallContextBuilder) {
        this.externalMallContextMapper = externalMallContextMapper;
        this.externalMallContextBuilder = externalMallContextBuilder;
    }

    @Override
    public SyncStepResult execute(ExternalMallPreProductContext context) {
        ExternalMallProductContext.Builder builder = externalMallContextBuilder.getBuilder();
        ExternalMallImageGroupContext externalMallImageGroupContext = externalMallContextMapper.generateImageGroupContext(context);
        builder.withImages(externalMallImageGroupContext);
        return SyncStepResult.success(SyncStep.TRANSFORM_IMAGE, builder);
    }

    @Override
    public SyncStep getStep() {
        return SyncStep.TRANSFORM_IMAGE;
    }


}
