package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ProductImageType;
import com.ryuqq.setof.db.core.product.image.ProductGroupImageEntity;

public record ProductGroupImageCommand(
        Long id,
        ProductImageType productImageType,
        String imageUrl,
        boolean deleteYn
) {

    public ProductGroupImageEntity toEntity(long productGroupId) {
        if(id == null) return new ProductGroupImageEntity(productGroupId, productImageType, imageUrl, imageUrl);
        else return new ProductGroupImageEntity(id, productGroupId, productImageType, imageUrl, imageUrl, deleteYn);
    }
}
