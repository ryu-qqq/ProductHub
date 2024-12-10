package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.ExternalProductImageEntity;

public record ExternalProductImageCommand(
        long productGroupId,
        long siteId,
        String externalProductId,
        int displayOrder,
        String imageUrl,
        String originUrl
) {
    public ExternalProductImageEntity toEntity() {
        return new ExternalProductImageEntity(
                productGroupId,
                siteId,
                externalProductId,
                displayOrder,
                imageUrl,
                originUrl
        );
    }

}
