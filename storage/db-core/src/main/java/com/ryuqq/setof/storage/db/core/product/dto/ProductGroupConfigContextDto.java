package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public class ProductGroupConfigContextDto {

    private ProductGroupConfigDto productGroupConfigDto;
    private List<ProductGroupNameConfigDto> productGroupNameConfigs;

    protected ProductGroupConfigContextDto() {}

    @QueryProjection
    public ProductGroupConfigContextDto(ProductGroupConfigDto productGroupConfigDto, List<ProductGroupNameConfigDto> productGroupNameConfigs) {
        this.productGroupConfigDto = productGroupConfigDto;
        this.productGroupNameConfigs = productGroupNameConfigs;
    }

    public ProductGroupConfigDto getProductGroupConfigDto() {
        return productGroupConfigDto;
    }

    public List<ProductGroupNameConfigDto> getProductGroupNameConfigs() {
        return productGroupNameConfigs;
    }
}
