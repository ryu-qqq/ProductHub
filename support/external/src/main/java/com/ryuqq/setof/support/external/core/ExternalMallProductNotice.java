package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.Origin;

public record ExternalMallProductNotice(
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
