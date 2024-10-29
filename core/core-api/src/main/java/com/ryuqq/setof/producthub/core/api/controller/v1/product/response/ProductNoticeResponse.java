package com.ryuqq.setof.producthub.core.api.controller.v1.product.response;

import com.ryuqq.setof.core.Origin;

public record ProductNoticeResponse(
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
