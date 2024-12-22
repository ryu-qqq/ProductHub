package com.ryuqq.setof.domain.core.site.external;

public record ExternalProductImage(
        Long externalProductImageId,
        long productGroupId,
        String externalProductGroupId,
        int displayOrder,
        String imageUrl,
        String originUrl
) {}
