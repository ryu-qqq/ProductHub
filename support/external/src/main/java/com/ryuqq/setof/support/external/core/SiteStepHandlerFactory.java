package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.buyma.BuyMaContextHandlerFactory;
import com.ryuqq.setof.support.external.core.oco.OcoContextHandlerFactory;
import com.ryuqq.setof.support.external.core.sellic.SellicContextHandlerFactory;
import com.ryuqq.setof.support.external.core.shein.SheInContextHandlerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SiteStepHandlerFactory {

    private final ExternalMallContextMapperProvider externalMallContextMapperProvider;
    private final ExternalMallContextBuilderProvider externalMallContextBuilderProvider;
    private final ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider;

    public SiteStepHandlerFactory(ExternalMallContextMapperProvider externalMallContextMapperProvider, ExternalMallContextBuilderProvider externalMallContextBuilderProvider, ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider) {
        this.externalMallContextMapperProvider = externalMallContextMapperProvider;
        this.externalMallContextBuilderProvider = externalMallContextBuilderProvider;
        this.externalMallProductRegistrationServiceProvider = externalMallProductRegistrationServiceProvider;
    }


    public List<SyncStepHandler> createHandlers(SiteName siteName) {
        ExternalMallContextMapper externalMallContextMapper = externalMallContextMapperProvider.get(siteName);
        ExternalMallContextBuilder externalMallContextBuilder = externalMallContextBuilderProvider.get(siteName);

        return switch (siteName) {
            case BUYMA -> BuyMaContextHandlerFactory.createHandlers(externalMallContextMapper, externalMallContextBuilder, externalMallProductRegistrationServiceProvider);
            case SHEIN -> SheInContextHandlerFactory.createHandlers();
            case SELLIC -> SellicContextHandlerFactory.createHandlers();
            case OCO -> OcoContextHandlerFactory.createHandlers();
            default -> throw new IllegalArgumentException("Unsupported site: " + siteName);
        };
    }

}
