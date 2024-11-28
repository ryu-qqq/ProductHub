package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ProductDetailDescriptionDto {
    private String detailDescription;

    protected ProductDetailDescriptionDto() {}

    @QueryProjection
    public ProductDetailDescriptionDto(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

}
