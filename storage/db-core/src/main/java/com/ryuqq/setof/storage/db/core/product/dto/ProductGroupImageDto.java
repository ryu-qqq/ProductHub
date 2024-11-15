package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.core.ProductImageType;

public class ProductGroupImageDto {

    private long productGroupImageId;
    private long productGroupId;
    private ProductImageType productImageType;
    private String imageUrl;
    private String originUrl;

    protected ProductGroupImageDto() {}

    @QueryProjection
    public ProductGroupImageDto(long productGroupImageId, long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl) {
        this.productGroupImageId = productGroupImageId;
        this.productGroupId = productGroupId;
        this.productImageType = productImageType;
        this.imageUrl = imageUrl;
        this.originUrl = originUrl;
    }

    public long getProductGroupImageId() {
        return productGroupImageId;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public ProductImageType getProductImageType() {
        return productImageType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOriginUrl() {
        return originUrl;
    }


}
