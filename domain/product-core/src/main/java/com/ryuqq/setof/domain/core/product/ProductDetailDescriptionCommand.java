package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionEntity;

public record ProductDetailDescriptionCommand (
        String detailDescription
){

    public ProductDetailDescriptionEntity toEntity(long productGroupId){
        return new ProductDetailDescriptionEntity(productGroupId, detailDescription);
    }
}
