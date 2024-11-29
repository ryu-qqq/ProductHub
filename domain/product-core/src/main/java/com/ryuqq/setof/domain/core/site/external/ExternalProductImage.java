package com.ryuqq.setof.domain.core.site.external;

public record ExternalProductImage(
        long productGroupId,
        String externalProductId,
        int displayOrder,
        String imageUrl,
        String originUrl
) {

}
