package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.support.external.core.*;

import java.util.List;

public class SellicContextHandlerFactory {

    public static List<SyncStepHandler> createHandlers(
            ExternalMallContextMapperProvider externalMallContextMapperProvider,
            ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider
    ) {
        return List.of(
                new TransformProductDetailStepHandler(externalMallContextMapperProvider),
                new RequestStepHandler(externalMallProductRegistrationServiceProvider)
        );
    }
}
