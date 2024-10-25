package com.ryuqq.setof.storage.db.core.brand.dao;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.domain.core.brand.Brand;

public class BrandDao {

    private long id;
    private String brandName;
    private String brandIconImageUrl;
    private boolean displayYn;

    protected BrandDao(){}

    @QueryProjection
    public BrandDao(long id, String brandName, String brandIconImageUrl, boolean displayYn) {
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

    public Brand toBrand(){
        return new Brand(id, brandName, brandIconImageUrl, displayYn);
    }
}
