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
    private final ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider;

    public SiteStepHandlerFactory(ExternalMallContextMapperProvider externalMallContextMapperProvider, ExternalMallProductRegistrationServiceProvider externalMallProductRegistrationServiceProvider) {
        this.externalMallContextMapperProvider = externalMallContextMapperProvider;
        this.externalMallProductRegistrationServiceProvider = externalMallProductRegistrationServiceProvider;
    }

    public List<SyncStepHandler> createHandlers(SiteName siteName) {
        return switch (siteName) {
            case BUYMA -> BuyMaContextHandlerFactory.createHandlers(externalMallContextMapperProvider, externalMallProductRegistrationServiceProvider);
            case SHEIN -> SheInContextHandlerFactory.createHandlers(externalMallContextMapperProvider, externalMallProductRegistrationServiceProvider);
            case OCO -> OcoContextHandlerFactory.createHandlers(externalMallContextMapperProvider, externalMallProductRegistrationServiceProvider);
            case SELLIC -> SellicContextHandlerFactory.createHandlers(externalMallContextMapperProvider, externalMallProductRegistrationServiceProvider);
            default -> throw new IllegalArgumentException("Unsupported site: " + siteName);
        };
    }

}
