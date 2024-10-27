package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.core.ProductImageType;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;

public record ProductGroupImageCommand(
        ProductImageType productImageType,
        String imageUrl
) {
    public ProductGroupImageEntity toEntity(long productGroupId) {
        return new ProductGroupImageEntity(productGroupId, productImageType, imageUrl);
    }
}
