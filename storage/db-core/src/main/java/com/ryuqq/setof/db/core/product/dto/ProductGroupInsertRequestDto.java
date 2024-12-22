package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;

public final class ProductGroupInsertRequestDto {

    private final long productGroupId;
    private final String requestBody;

    @QueryProjection
    public ProductGroupInsertRequestDto(long productGroupId, String requestBody) {
        this.productGroupId = productGroupId;
        this.requestBody = requestBody;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
