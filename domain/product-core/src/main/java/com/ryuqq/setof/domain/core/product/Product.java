package com.ryuqq.setof.domain.core.product;

import java.math.BigDecimal;
import java.util.List;

public record Product(
        boolean soldOutYn,
        boolean displayYn,
        int quantity,
        BigDecimal additionalPrice,
        List<Option> options
) {}
