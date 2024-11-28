package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.List;

public record OptionsResult(
        long productGroupId,
        List<String> originalOptions,
        NormalizedOptions normalizedOptions

) implements BatchResult {


    @Override
    public long getProductGroupId() {
        return productGroupId;
    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.OPTIONS;
    }

    public record NormalizedOptions(
            List<String> sizes,
            String unit

    ) {}

}
