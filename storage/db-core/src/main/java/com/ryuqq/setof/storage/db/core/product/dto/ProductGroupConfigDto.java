package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ProductGroupConfigDto {

    private long configId;
    private long productGroupId;
    private long sellerId;
    private long brandId;
    private long categoryId;
    private Long colorId;
    private boolean activeYn;

    protected ProductGroupConfigDto() {}

    @QueryProjection
    public ProductGroupConfigDto(long configId, long productGroupId, long sellerId, long brandId, long categoryId, Long colorId, boolean activeYn) {
        this.configId = configId;
        this.productGroupId = productGroupId;
        this.sellerId = sellerId;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.colorId = colorId;
        this.activeYn = activeYn;
    }

    public long getConfigId() {
        return configId;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public long getBrandId() {
        return brandId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public Long getColorId() {
        return colorId;
    }

    public boolean isActiveYn() {
        return activeYn;
    }

}
