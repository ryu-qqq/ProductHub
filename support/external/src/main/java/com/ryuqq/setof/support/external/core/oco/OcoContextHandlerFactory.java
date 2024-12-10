package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.support.external.core.*;

import java.util.List;

public class OcoContextHandlerFactory {

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
