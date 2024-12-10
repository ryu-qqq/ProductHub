package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.*;

import java.util.List;

public class SheInContextHandlerFactory {

    public static List<SyncStepHandler> createHandlers(
            ExternalMallContextMapperProvider externalMallContextMapperProvider,
            ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider
    ) {
        return List.of(
                new TransformProductDetailStepHandler(externalMallContextMapperProvider),
                new TransformBrandCategoryStepHandler(externalMallContextMapperProvider),
                new TransformImageStepHandler(externalMallContextMapperProvider),
                new TransformOptionStepHandler(externalMallContextMapperProvider),
                new RequestStepHandler(externalMallProductRegistrationServiceProvider)
        );
    }

}
