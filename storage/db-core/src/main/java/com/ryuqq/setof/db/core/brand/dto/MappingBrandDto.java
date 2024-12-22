package com.ryuqq.setof.storage.db.core.brand.dto;

import com.querydsl.core.annotations.QueryProjection;

public class MappingBrandDto{
    private final String externalBrandId;
    private final long brandId;
    private final String brandName;

    @QueryProjection
    public MappingBrandDto(String externalBrandId, long brandId, String brandName) {
        this.externalBrandId = externalBrandId;
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public String getExternalBrandId() {
        return externalBrandId;
    }

    public long getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }
}
