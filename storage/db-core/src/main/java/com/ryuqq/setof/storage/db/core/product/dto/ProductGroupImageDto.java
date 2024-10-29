package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.core.ProductImageType;

public class ProductGroupImageDto {

    private ProductImageType productImageType;
    private String imageUrl;

    protected ProductGroupImageDto() {}

    @QueryProjection
    public ProductGroupImageDto(ProductImageType productImageType, String imageUrl) {
        this.productImageType = productImageType;
        this.imageUrl = imageUrl;
    }

    public ProductImageType getProductImageType() {
        return productImageType;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
