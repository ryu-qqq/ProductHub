package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductDataType;

public record GptDescriptionResult(
        long productGroupId,
        String description
) implements GptBatchResult {
    @Override
    public long getProductGroupId() {
        return productGroupId;
    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.DESCRIPTION;
    }
}
