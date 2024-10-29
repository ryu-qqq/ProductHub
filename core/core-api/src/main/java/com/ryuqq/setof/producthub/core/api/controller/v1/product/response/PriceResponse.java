package com.ryuqq.setof.producthub.core.api.controller.v1.product.response;

import java.math.BigDecimal;

public record PriceResponse(
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        BigDecimal salePrice,
        BigDecimal directDiscountPrice,
        int directDiscountRate,
        int discountRate
) {}
