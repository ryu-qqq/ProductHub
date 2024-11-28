package com.ryuqq.setof.domain.core.product;

import java.util.List;

public record ProductGroupConfigContextAggregate(
        List<ProductGroupConfig> productGroupConfigs,
        List<ProductGroupNameConfig> productGroupNameConfigs,
        List<ProductGroupDiscountConfig> productGroupDiscountConfigs
) {

    public static ProductGroupConfigContextAggregate of(List<ProductGroupConfig> productGroupConfigs,
                                                 List<ProductGroupNameConfig> productGroupNameConfigs,
                                                 List<ProductGroupDiscountConfig> productGroupDiscountConfigs){
        return new ProductGroupConfigContextAggregate(
                productGroupConfigs,
                productGroupNameConfigs,
                productGroupDiscountConfigs
        );
    }
}
