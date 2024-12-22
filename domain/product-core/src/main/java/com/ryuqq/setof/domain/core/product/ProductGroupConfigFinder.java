package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.group.ProductGroupConfigQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupConfigFinder {

    private final ProductGroupConfigMapper productGroupConfigMapper;
    private final ProductGroupConfigQueryRepository productGroupConfigQueryRepository;

    public ProductGroupConfigFinder(ProductGroupConfigMapper productGroupConfigMapper, ProductGroupConfigQueryRepository productGroupConfigQueryRepository) {
        this.productGroupConfigMapper = productGroupConfigMapper;
        this.productGroupConfigQueryRepository = productGroupConfigQueryRepository;
    }

    public List<ProductGroupConfig> fetchByConfigIds(List<Long> configIds) {
        return productGroupConfigQueryRepository.fetchByConfigIds(configIds).stream()
                .map(productGroupConfigMapper::toDomain)
                .toList();
    }

    public List<ProductGroupConfig> fetchByProductGroupIds(List<Long> productGroupIds) {
        return productGroupConfigQueryRepository.fetchByConfigIds(productGroupIds).stream()
                .map(productGroupConfigMapper::toDomain)
                .toList();
    }

}
