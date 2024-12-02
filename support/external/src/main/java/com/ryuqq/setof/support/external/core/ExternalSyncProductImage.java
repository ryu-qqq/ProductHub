package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.ProductImageType;

public record ExternalSyncProductImage(
        ProductImageType productImageType,
        String imageUrl,
        String originUrl
) {}
