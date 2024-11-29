package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.Objects;

public class ProductProcessingResultDto {
    private long productGroupId;
    private ProductDataType productDataType;
    private String result;

    @QueryProjection
    public ProductProcessingResultDto(long productGroupId, ProductDataType productDataType, String result) {
        this.productGroupId = productGroupId;
        this.productDataType = productDataType;
        this.result = result;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public ProductDataType getProductDataType() {
        return productDataType;
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProductProcessingResultDto that = (ProductProcessingResultDto) object;
        return productGroupId == that.productGroupId && productDataType == that.productDataType && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupId, productDataType, result);
    }
}
