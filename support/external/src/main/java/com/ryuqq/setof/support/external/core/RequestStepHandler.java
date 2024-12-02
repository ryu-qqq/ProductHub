package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;
import com.ryuqq.setof.support.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestStepHandler implements SyncStepHandler {

    private final ExternalMallContextBuilder externalMallContextBuilder;
    private final ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider;

    public RequestStepHandler(ExternalMallContextBuilder externalMallContextBuilder, ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider) {
        this.externalMallContextBuilder = externalMallContextBuilder;
        this.externalMallProductRegistrationServiceProvider = externalMallProductRegistrationServiceProvider;
    }

    @Override
    public SyncStepResult execute(ExternalMallPreProductContext context) {
        ExternalMallProductContext.Builder builder = externalMallContextBuilder.getBuilder();

        @SuppressWarnings("unchecked")
        ExternalMallProductRegistrationService<ExternalMallProductContext> externalMallProductRegistrationService =
                (ExternalMallProductRegistrationService<ExternalMallProductContext>) externalMallProductRegistrationServiceProvider.get(context.siteName());

        ExternalMallSyncResponse response = externalMallProductRegistrationService.registration(builder.build());
        ExternalMallRequestStatus status = response.externalMallRequestStatus();
        if (!status.success()) {
            return SyncStepResult.failure(SyncStep.REQUEST, status.statusCode(), status.errorDetails(), builder.build());
        }

        return SyncStepResult.success(SyncStep.REQUEST, response);
    }

    @Override
    public SyncStep getStep() {
        return SyncStep.REQUEST;
    }

}
