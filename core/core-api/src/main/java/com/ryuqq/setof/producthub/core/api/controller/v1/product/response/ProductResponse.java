package com.ryuqq.setof.producthub.core.api.controller.v1.product.response;

import java.math.BigDecimal;
import java.util.Set;

public record ProductResponse(
        long productGroupId,
        long productId,
        int quantity,
        boolean soldOutYn,
        boolean displayYn,
        String option,
        Set<OptionResponse> options,
        BigDecimal additionalPrice
) {}
