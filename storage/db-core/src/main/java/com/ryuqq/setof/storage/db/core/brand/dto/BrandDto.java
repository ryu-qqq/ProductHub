package com.ryuqq.setof.storage.db.core.brand.dto;

import com.querydsl.core.annotations.QueryProjection;

public class BrandDto {

    private long id;
    private String brandName;
    private String brandIconImageUrl;
    private boolean displayYn;

    protected BrandDto(){}

    @QueryProjection
    public BrandDto(long id, String brandName, String brandIconImageUrl, boolean displayYn) {
        this.id = id;
        this.brandName = brandName;
        this.brandIconImageUrl = brandIconImageUrl;
        this.displayYn = displayYn;
    }

    public long getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getBrandIconImageUrl() {
        return brandIconImageUrl;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }

}
