package com.ryuqq.setof.support.external.core.buyma.domain;

import com.ryuqq.setof.support.external.core.ExternalMallCategoryAndBrandContext;

public record BuyMaCategoryAndBrandContext(
        String categoryId,
        String brandId,
        String brandName
) implements ExternalMallCategoryAndBrandContext {

    @Override
    public String getCategoryId() {
        return categoryId;
    }

    @Override
    public String getBrandId() {
        return brandId;
    }

    @Override
    public String getBrandName() {
        return brandName;
    }

}
