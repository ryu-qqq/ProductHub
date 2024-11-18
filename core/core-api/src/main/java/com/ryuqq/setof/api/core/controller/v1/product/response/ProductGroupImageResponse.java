package com.ryuqq.setof.api.core.controller.v1.product.response;

import com.ryuqq.setof.enums.core.ProductImageType;

public record ProductGroupImageResponse(
        ProductImageType productImageType,
        String imageUrl
) {}
