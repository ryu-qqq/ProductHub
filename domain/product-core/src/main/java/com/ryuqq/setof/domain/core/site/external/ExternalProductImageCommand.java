package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.db.core.site.external.ExternalProductImageEntity;

public record ExternalProductImageCommand(
        long productGroupId,
        long siteId,
        String externalProductGroupId,
        int displayOrder,
        String imageUrl,
        String originUrl
) {
    public ExternalProductImageEntity toEntity() {
        return new ExternalProductImageEntity(
                productGroupId,
                siteId,
                externalProductGroupId,
                displayOrder,
                imageUrl,
                originUrl
        );
    }

}
