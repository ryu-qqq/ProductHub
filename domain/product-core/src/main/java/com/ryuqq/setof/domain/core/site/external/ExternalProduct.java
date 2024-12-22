package com.ryuqq.setof.domain.core.site.external;

import java.math.BigDecimal;

public record ExternalProduct(
        String externalProductGroupId,
        String externalProductId,
        String optionValue,
        int quantity,
        BigDecimal additionalPrice,
        boolean soldOutYn,
        boolean displayYn
) {}
