package com.ryuqq.setof.support.external.core.buyma.domain;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.ExternalMallContextBuilder;
import com.ryuqq.setof.support.external.core.ExternalMallProductContext;
import org.springframework.stereotype.Component;

@Component
public class BuyMaContextBuilder implements ExternalMallContextBuilder {

    private final BuyMaProductContextBuilder builder = new BuyMaProductContextBuilder();

    @Override
    public ExternalMallProductContext.Builder getBuilder() {
        return builder;
    }

    @Override
    public SiteName getSiteName() {
        return SiteName.BUYMA;
    }
}
