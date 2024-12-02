package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;
import org.springframework.stereotype.Component;

@Component
public class TransformOptionStepHandler implements SyncStepHandler {
    private final ExternalMallContextMapper externalMallContextMapper;
    private final ExternalMallContextBuilder externalMallContextBuilder;

    public TransformOptionStepHandler(ExternalMallContextMapper externalMallContextMapper, ExternalMallContextBuilder externalMallContextBuilder) {
        this.externalMallContextMapper = externalMallContextMapper;
        this.externalMallContextBuilder = externalMallContextBuilder;
    }

    @Override
    public SyncStepResult execute(ExternalMallPreProductContext context) {
        ExternalMallProductContext.Builder builder = externalMallContextBuilder.getBuilder();
        ExternalMallOptionContext externalMallOptionContext = externalMallContextMapper.generateOptionContext(context);
        builder.withOptions(externalMallOptionContext);
        return SyncStepResult.success(SyncStep.TRANSFORM_OPTION, builder);
    }

    @Override
    public SyncStep getStep() {
        return SyncStep.TRANSFORM_OPTION;
    }


}
