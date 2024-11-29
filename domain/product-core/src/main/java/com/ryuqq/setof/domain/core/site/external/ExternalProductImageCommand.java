package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.ExternalProductImageEntity;
import com.ryuqq.setof.support.external.core.ExternalMallImageResult;

public record ExternalProductImageCommand(
        long productGroupId,
        String externalProductId,
        int displayOrder,
        String imageUrl,
        String originUrl
) {

    public static ExternalProductImageCommand of(ExternalMallImageResult externalMallImageResult){
        return new ExternalProductImageCommand(
                externalMallImageResult.productGroupId(),
                externalMallImageResult.externalProductId(),
                externalMallImageResult.displayOrder(),
                externalMallImageResult.imageUrl(),
                externalMallImageResult.originUrl()
        );
    }

    public ExternalProductImageEntity toEntity() {
        return new ExternalProductImageEntity(
                productGroupId,
                externalProductId,
                displayOrder,
                imageUrl,
                originUrl
        );
    }

}
