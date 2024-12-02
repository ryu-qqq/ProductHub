package com.ryuqq.setof.support.external.core.buyma.domain;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.support.external.core.ExternalMallPriceContext;

import java.math.BigDecimal;


public record BuyMaPriceContext(
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        Origin countryCode
) implements ExternalMallPriceContext {

    @Override
    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    @Override
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    @Override
    public Origin getCurrency() {
        return countryCode;
    }

}
