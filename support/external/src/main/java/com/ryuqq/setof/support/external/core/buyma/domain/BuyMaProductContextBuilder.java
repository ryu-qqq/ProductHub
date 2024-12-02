package com.ryuqq.setof.support.external.core.buyma.domain;

import com.ryuqq.setof.support.external.core.*;

public class BuyMaProductContextBuilder implements ExternalMallProductContext.Builder {

    private long productGroupId;
    private long setOfProductGroupId;
    private ExternalMallProductDetailContext detailContext;
    private ExternalMallPriceContext priceContext;
    private ExternalMallCategoryAndBrandContext categoryAndBrandContext;
    private ExternalMallImageGroupContext imageGroupContext;
    private ExternalMallOptionContext optionContext;

    @Override
    public BuyMaProductContextBuilder withDetailContext(long productGroupId, long setOfProductGroupId, ExternalMallProductDetailContext detailContext) {
        this.productGroupId = productGroupId;
        this.setOfProductGroupId = setOfProductGroupId;
        this.detailContext = detailContext;
        return this;
    }

    @Override
    public BuyMaProductContextBuilder withCategoryAndBrand(ExternalMallCategoryAndBrandContext categoryAndBrandContext) {
        this.categoryAndBrandContext = categoryAndBrandContext;
        return this;
    }

    @Override
    public BuyMaProductContextBuilder withImages(ExternalMallImageGroupContext images) {
        this.imageGroupContext = images;
        return this;
    }

    @Override
    public BuyMaProductContextBuilder withOptions(ExternalMallOptionContext optionContext) {
        this.optionContext = optionContext;
        return this;
    }

    @Override
    public BuyMaProductContextBuilder withPrice(ExternalMallPriceContext priceContext) {
        this.priceContext = priceContext;
        return this;
    }

    @Override
    public ExternalMallProductContext build() {
        return new BuyMaProductContext(productGroupId, setOfProductGroupId, detailContext, categoryAndBrandContext, imageGroupContext, optionContext, priceContext);
    }

}
