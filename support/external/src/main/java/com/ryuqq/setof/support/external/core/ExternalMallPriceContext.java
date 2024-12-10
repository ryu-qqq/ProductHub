package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.Origin;

import java.math.BigDecimal;

public record ExternalMallPriceContext(
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        Origin currency
) {}
