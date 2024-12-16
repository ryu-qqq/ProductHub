package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;
import org.springframework.stereotype.Component;

@Component
public class RequestStepHandler implements SyncStepHandler {

    private final ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider;

    public RequestStepHandler(ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider) {
        this.externalMallProductRegistrationServiceProvider = externalMallProductRegistrationServiceProvider;
    }


    @Override
    public SyncStepResult execute(ExternalMallPreProductContext context, ExternalMallProductContext.Builder builder) {
        ExternalMallProductRegistrationService externalMallProductRegistrationService = externalMallProductRegistrationServiceProvider.get(context.siteName());
        SyncResult response = externalMallProductRegistrationService.registration(context, builder.build());

        if (!response.success()) {
            return SyncStepResult.failure(SyncStep.REQUEST, response.statusCode(), response.message(), builder, response.requestBody());
        }

        builder.withExternalProductId(response.externalProductGroupId());
        return SyncStepResult.success(SyncStep.REQUEST,  response.status(), builder, response.requestBody());
    }

    @Override
    public SyncStep getStep() {
        return SyncStep.REQUEST;
    }

}
