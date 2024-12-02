package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.Origin;

import java.math.BigDecimal;

public interface ExternalMallPriceContext {

    BigDecimal getRegularPrice();
    BigDecimal getCurrentPrice();
    Origin getCurrency();

}
