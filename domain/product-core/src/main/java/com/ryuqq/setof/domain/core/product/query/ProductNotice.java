package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.core.Origin;

public record ProductNotice(
        String material,
        String color,
        String size,
        String maker,
        Origin origin,
        String washingMethod,
        String yearMonth,
        String assuranceStandard,
        String asPhone
) {}
