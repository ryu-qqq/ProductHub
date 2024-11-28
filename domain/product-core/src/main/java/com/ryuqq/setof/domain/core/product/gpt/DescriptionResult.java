package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductDataType;

public record DescriptionResult(
        long productGroupId,
        String description
) implements BatchResult {
    @Override
    public long getProductGroupId() {
        return productGroupId;
    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.DESCRIPTION;
    }
}
