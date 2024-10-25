package com.ryuqq.setof.producthub.core.api.controller.v1.brand.response;

public record BrandResponse (
        long id,
        String brandName,
        String brandIconImageUrl,
        boolean displayYn
){}
