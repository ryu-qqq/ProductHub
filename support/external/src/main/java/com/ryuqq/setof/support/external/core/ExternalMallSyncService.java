package com.ryuqq.setof.support.external.core;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalMallSyncService {

    private final ExternalSyncProductMapperProvider externalSyncProductMapperProvider;
    private final ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider;

    public ExternalMallSyncService(ExternalSyncProductMapperProvider externalSyncProductMapperProvider, ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider) {
        this.externalSyncProductMapperProvider = externalSyncProductMapperProvider;
        this.externalMallProductRegistrationServiceProvider = externalMallProductRegistrationServiceProvider;
    }

    public List<ExternalMallRegistrationResult> sync(List<ExternalMallProductContext> externalMallProductContexts) {
        List<ExternalMallRegistrationResult> results = new ArrayList<>();

        for (ExternalMallProductContext context : externalMallProductContexts) {
            results.add(handleSync(context));
        }

        return results;
    }

    @SuppressWarnings("unchecked")
    private ExternalMallRegistrationResult handleSync(ExternalMallProductContext context) {
        try {
            ExternalMallRegistrationRequest transform = transformRequest(context);

            return registerProduct(context, transform);

        } catch (Exception e) {
            return ExternalMallRegistrationResult.failedResult(
                    context.productGroup().productGroupId(),
                    e.getMessage(),
                    context.siteId()
            );
        }
    }

    private ExternalMallRegistrationRequest transformRequest(ExternalMallProductContext context) {
        ExternalSyncProductMapper externalSyncProductMapper = externalSyncProductMapperProvider.get(context.siteName());
        return externalSyncProductMapper.transform(context);
    }


    @SuppressWarnings("unchecked")
    private ExternalMallRegistrationResult registerProduct(ExternalMallProductContext context, ExternalMallRegistrationRequest transform) {
        ExternalMallProductRegistrationService<ExternalMallProductPayload> externalMallProductRegistrationService =
                (ExternalMallProductRegistrationService<ExternalMallProductPayload>) externalMallProductRegistrationServiceProvider.get(context.siteName());
        return externalMallProductRegistrationService.registration(
                transform.productGroupId(),
                transform.siteId(),
                transform.externalMallProductPayload()
        );
    }


}
