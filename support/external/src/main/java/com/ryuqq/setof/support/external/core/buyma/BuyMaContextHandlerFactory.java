package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.*;

import java.util.List;

public class BuyMaContextHandlerFactory {

    public static List<SyncStepHandler> createHandlers(
             ExternalMallContextMapperProvider externalMallContextMapperProvider,
             ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider) {

        return List.of(
                new TransformProductDetailStepHandler(externalMallContextMapperProvider),
                new RequestStepHandler(externalMallProductRegistrationServiceProvider)
        );
    }

}
