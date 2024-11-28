package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.Objects;

public class ExternalProductProcessingResultDto {
    private long productGroupId;
    private ProductDataType productDataType;
    private String result;

    @QueryProjection
    public ExternalProductProcessingResultDto(long productGroupId, ProductDataType productDataType, String result) {
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
        ExternalProductProcessingResultDto that = (ExternalProductProcessingResultDto) object;
        return productGroupId == that.productGroupId && productDataType == that.productDataType && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupId, productDataType, result);
    }
}
