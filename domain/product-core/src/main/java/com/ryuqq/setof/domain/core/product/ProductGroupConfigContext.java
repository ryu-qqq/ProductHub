package com.ryuqq.setof.domain.core.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductGroupConfigContext {
    private final ProductGroupConfig productGroupConfig;
    private List<ProductGroupNameConfig> productGroupNameConfigs;
    private List<ProductGroupDiscountConfig> productGroupDiscountConfigs;

    public ProductGroupConfigContext(ProductGroupConfig productGroupConfig) {
        this.productGroupConfig = productGroupConfig;
    }

    public ProductGroupConfigContext(ProductGroupConfig productGroupConfig, List<ProductGroupNameConfig> productGroupNameConfigs, List<ProductGroupDiscountConfig> productGroupDiscountConfigs) {
        this.productGroupConfig = productGroupConfig;
        this.productGroupNameConfigs = Objects.requireNonNullElseGet(productGroupNameConfigs, ArrayList::new);
        this.productGroupDiscountConfigs = Objects.requireNonNullElseGet(productGroupDiscountConfigs, ArrayList::new);
    }

    public long getSellerId(){
        return productGroupConfig.getSellerId();
    }

    public long getProductGroupId(){
        return productGroupConfig.getProductGroupId();
    }

    public long getConfigId(){
        return productGroupConfig.getConfigId();
    }

    public ProductGroupConfig getProductGroupConfig() {
        return productGroupConfig;
    }

    public List<ProductGroupNameConfig> getProductGroupNameConfigs() {
        return productGroupNameConfigs;
    }

    public List<ProductGroupDiscountConfig> getProductGroupDiscountConfigs() {
        return productGroupDiscountConfigs;
    }
}
