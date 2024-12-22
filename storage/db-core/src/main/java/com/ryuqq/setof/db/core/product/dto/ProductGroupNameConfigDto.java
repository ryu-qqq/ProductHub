package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.Origin;

public class ProductGroupNameConfigDto {

    private long productGroupNameConfigId;
    private long configId;
    private long productGroupId;
    private Origin countryCode;
    private String customName;

    protected ProductGroupNameConfigDto() {}

    @QueryProjection
    public ProductGroupNameConfigDto(long productGroupNameConfigId, long configId, long productGroupId, Origin countryCode, String customName) {
        this.productGroupNameConfigId = productGroupNameConfigId;
        this.configId = configId;
        this.productGroupId = productGroupId;
        this.countryCode = countryCode;
        this.customName = customName;
    }

    public ProductGroupNameConfigDto(long productGroupNameConfigId, String customName){
        this.productGroupNameConfigId = productGroupNameConfigId;
        this.customName = customName;
    }

    public long getProductGroupNameConfigId() {
        return productGroupNameConfigId;
    }

    public long getConfigId() {
        return configId;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public Origin getCountryCode() {
        return countryCode;
    }

    public String getCustomName() {
        return customName;
    }
}
