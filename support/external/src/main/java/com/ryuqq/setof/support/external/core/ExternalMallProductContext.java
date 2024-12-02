package com.ryuqq.setof.support.external.core;

public interface ExternalMallProductContext {

    long getProductGroupId();
    long getSetOfProductGroupId();
    ExternalMallProductDetailContext getDetailContext();
    ExternalMallImageGroupContext getExternalMallImageGroupContext();
    ExternalMallOptionContext getExternalMallOptionContext();
    ExternalMallPriceContext getPriceContext();
    ExternalMallCategoryAndBrandContext getExternalMallCategoryAndBrandContext();

    interface Builder {
        Builder withDetailContext(long productGroupId, long setOfProductGroupId, ExternalMallProductDetailContext detailContext);
        Builder withCategoryAndBrand(ExternalMallCategoryAndBrandContext categoryAndBrandContext);
        Builder withImages(ExternalMallImageGroupContext images);
        Builder withOptions(ExternalMallOptionContext externalMallOptionContext);
        Builder withPrice(ExternalMallPriceContext priceContext);
        ExternalMallProductContext build();
    }

}