package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupConfigContextFinder {

    private final ProductGroupConfigFinder productGroupConfigFinder;
    private final ProductGroupConfigContextAssembler configContextAssembler;
    private final ProductGroupConfigContextMapper productGroupConfigContextMapper;


    public ProductGroupConfigContextFinder(ProductGroupConfigFinder productGroupConfigFinder, ProductGroupConfigContextAssembler configContextAssembler, ProductGroupConfigContextMapper productGroupConfigContextMapper) {
        this.productGroupConfigFinder = productGroupConfigFinder;
        this.configContextAssembler = configContextAssembler;
        this.productGroupConfigContextMapper = productGroupConfigContextMapper;
    }

    public List<ProductGroupConfigContext> fetchByConfigIds(List<Long> configIds){
        List<ProductGroupConfig> productGroupConfigs = productGroupConfigFinder.fetchByConfigIds(configIds);
        if(productGroupConfigs.isEmpty()) return List.of();

        return assemble(productGroupConfigs);
    }

    public List<ProductGroupConfigContext> fetchByProductGroupIds(List<Long> productGroupIds){
        List<ProductGroupConfig> productGroupConfigs = productGroupConfigFinder.fetchByProductGroupIds(productGroupIds);
        if(productGroupConfigs.isEmpty()) return List.of();

        return assemble(productGroupConfigs);
    }

    private List<ProductGroupConfigContext> assemble(List<ProductGroupConfig> productGroupConfigs){
        ProductGroupConfigContextAggregate productGroupConfigContextAggregate = configContextAssembler.assemble(productGroupConfigs);
        return productGroupConfigContextMapper.toDomain(productGroupConfigContextAggregate);
    }

}
