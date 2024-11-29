package com.ryuqq.setof.support.external.core;

public record ExternalMallImageResult(
        long productGroupId,
        String externalProductId,
        int displayOrder,
        String originUrl,
        String imageUrl
) {
}
