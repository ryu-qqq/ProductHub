package com.ryuqq.setof.support.external.core;

import java.util.ArrayList;
import java.util.List;

public class ExternalMallProductContext {

    private final long productGroupId;
    private final long setOfProductGroupId;
    private final ExternalMallNameContext nameContext;
    private final ExternalMallImageContext imageGroupContext;
    private final ExternalMallPriceContext priceContext;
    private final ExternalMallOptionContext optionContext;
    private final ExternalMallCategoryAndBrandContext categoryAndBrandContext;
    private final ExternalMallProductStatusContext productStatusContext;
    private String externalProductGroupId;
    private List<ExternalMallSyncedOption> externalMallSyncedOptions;

    private ExternalMallProductContext(Builder builder) {
        this.productGroupId = builder.productGroupId;
        this.setOfProductGroupId = builder.setOfProductGroupId;
        this.nameContext = builder.nameContext;
        this.imageGroupContext = builder.imageGroupContext;
        this.priceContext = builder.priceContext;
        this.optionContext = builder.optionContext;
        this.categoryAndBrandContext = builder.categoryAndBrandContext;
        this.productStatusContext = builder.productStatusContext;
        this.externalProductGroupId = builder.externalProductGroupId;
        this.externalMallSyncedOptions = builder.externalMallSyncedOptions;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getSetOfProductGroupId() {
        return setOfProductGroupId;
    }

    public ExternalMallNameContext getNameContext() {
        return nameContext;
    }

    public ExternalMallImageContext getImageGroupContext() {
        return imageGroupContext;
    }

    public ExternalMallPriceContext getPriceContext() {
        return priceContext;
    }

    public ExternalMallOptionContext getOptionContext() {
        return optionContext;
    }

    public ExternalMallCategoryAndBrandContext getCategoryAndBrandContext() {
        return categoryAndBrandContext;
    }

    public ExternalMallProductStatusContext getProductStatusContext() {
        return productStatusContext;
    }

    public String getExternalProductGroupId() {
        return externalProductGroupId;
    }

    public List<ExternalMallSyncedOption> getExternalMallSyncedOptions() {
        return externalMallSyncedOptions;
    }

    public static class Builder {
        private long productGroupId;
        private long setOfProductGroupId;
        private ExternalMallNameContext nameContext;
        private ExternalMallImageContext imageGroupContext;
        private ExternalMallPriceContext priceContext;
        private ExternalMallOptionContext optionContext;
        private ExternalMallCategoryAndBrandContext categoryAndBrandContext;
        private ExternalMallProductStatusContext productStatusContext;
        private String externalProductGroupId;
        private List<ExternalMallSyncedOption> externalMallSyncedOptions;

        public Builder withNames(long productGroupId, long setOfProductGroupId, ExternalMallNameContext nameContext) {
            this.productGroupId = productGroupId;
            this.setOfProductGroupId = setOfProductGroupId;
            this.nameContext = nameContext;
            return this;
        }

        public Builder withImages(ExternalMallImageContext images) {
            this.imageGroupContext = images;
            return this;
        }

        public Builder withPrice(ExternalMallPriceContext priceContext) {
            this.priceContext = priceContext;
            return this;
        }

        public Builder withCategoryAndBrand(ExternalMallCategoryAndBrandContext categoryAndBrandContext) {
            this.categoryAndBrandContext = categoryAndBrandContext;
            return this;
        }

        public Builder withOptions(ExternalMallOptionContext optionContext) {
            this.optionContext = optionContext;
            return this;
        }

        public Builder withProductStatus(ExternalMallProductStatusContext productStatusContext) {
            this.productStatusContext = productStatusContext;
            return this;
        }


        public Builder withExternalProductGroupId(String externalProductId) {
            this.externalProductGroupId = externalProductId;
            return this;
        }


        public Builder withExternalMallSyncedOptions(List<ExternalMallSyncedOption> externalMallSyncedOptions) {
            this.externalMallSyncedOptions = externalMallSyncedOptions;
            return this;
        }


        public ExternalMallProductContext build() {
            return new ExternalMallProductContext(this);
        }
    }






}