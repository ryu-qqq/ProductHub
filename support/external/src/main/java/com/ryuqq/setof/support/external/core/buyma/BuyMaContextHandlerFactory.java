package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.*;

import java.util.List;

public class BuyMaContextHandlerFactory {

    public static List<SyncStepHandler> createHandlers(
            ExternalMallContextMapper externalMallContextMapper,
            ExternalMallContextBuilder externalMallContextBuilder,
            ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider) {

        return List.of(
                new TransformProductDetailStepHandler(externalMallContextMapper, externalMallContextBuilder),
                new TransformBrandCategoryStepHandler(externalMallContextMapper, externalMallContextBuilder),
                new TransformPriceStepHandler(externalMallContextMapper, externalMallContextBuilder),
                new TransformImageStepHandler(externalMallContextMapper, externalMallContextBuilder),
                new TransformOptionStepHandler(externalMallContextMapper, externalMallContextBuilder),
                new RequestStepHandler(externalMallContextBuilder, externalMallProductRegistrationServiceProvider)
        );
    }

}
