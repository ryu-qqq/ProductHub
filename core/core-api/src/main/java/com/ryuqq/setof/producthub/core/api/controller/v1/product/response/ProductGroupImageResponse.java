package com.ryuqq.setof.producthub.core.api.controller.v1.product.response;

import com.ryuqq.setof.core.ProductImageType;

public record ProductGroupImageResponse(
        ProductImageType productImageType,
        String imageUrl
) {}
