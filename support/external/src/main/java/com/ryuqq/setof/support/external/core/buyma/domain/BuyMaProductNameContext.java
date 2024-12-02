package com.ryuqq.setof.support.external.core.buyma.domain;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.support.external.core.ExternalMallNameContext;

public record BuyMaProductNameContext(
        String productName,
        String description,
        Origin countryCode
) implements ExternalMallNameContext {

    @Override
    public String getName() {
        return productName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Origin getCountryCode() {
        return countryCode;
    }

}
