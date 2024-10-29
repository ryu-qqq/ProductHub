package com.ryuqq.setof.domain.core.product.query;

import java.math.BigDecimal;
import java.util.Set;

public record ProductContext(
        long productGroupId,
        long productId,
        int quantity,
        boolean soldOutYn,
        boolean displayYn,
        String option,
        Set<OptionContext>options,
        BigDecimal additionalPrice
) {}
