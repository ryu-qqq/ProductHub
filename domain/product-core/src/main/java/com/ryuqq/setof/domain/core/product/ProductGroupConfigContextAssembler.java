package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupConfigContextAssembler {

    private final ProductGroupNameConfigFinder productGroupNameConfigFinder;

    public ProductGroupConfigContextAssembler(ProductGroupNameConfigFinder productGroupNameConfigFinder) {
        this.productGroupNameConfigFinder = productGroupNameConfigFinder;
    }

    public ProductGroupConfigContextAggregate assemble(List<ProductGroupConfig> productGroupConfigs) {
        List<Long> configIds = productGroupConfigs.stream().map(ProductGroupConfig::getConfigId).toList();

        return ProductGroupConfigContextAggregate.of(
                productGroupConfigs,
                productGroupNameConfigFinder.fetchByProductGroupIds(configIds),
                List.of()
        );
    }



}
