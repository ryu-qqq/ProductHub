package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.core.ProductImageType;

public record ProductGroupImage(
        ProductImageType productImageType,
        String imageUrl
) {}
