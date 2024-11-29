package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.domain.core.site.external.ExternalProductName;
import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.List;

public record GptTitleResult(
        long productGroupId,
        String originalTitle,
        List<ExternalProductName> externalProductNames,
        String brandName,
        String styleCode,
        String colorInTitle,
        List<String> deletedWords
) implements GptBatchResult {

    @Override
    public long getProductGroupId() {
        return productGroupId;
    }

    @Override
    public ProductDataType getProductDataType() {
        return ProductDataType.TITLE;
    }
}
