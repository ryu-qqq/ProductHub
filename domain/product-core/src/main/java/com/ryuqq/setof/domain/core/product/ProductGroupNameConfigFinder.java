package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.group.ProductGroupNameConfigQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupNameConfigFinder {

    private final ProductGroupNameMapper productGroupNameMapper;
    private final ProductGroupNameConfigQueryRepository productGroupNameConfigQueryRepository;

    public ProductGroupNameConfigFinder(ProductGroupNameMapper productGroupNameMapper, ProductGroupNameConfigQueryRepository productGroupNameConfigQueryRepository) {
        this.productGroupNameMapper = productGroupNameMapper;
        this.productGroupNameConfigQueryRepository = productGroupNameConfigQueryRepository;
    }

    public List<ProductGroupNameConfig> fetchByProductGroupIds(List<Long> productGroupIds){
        return productGroupNameConfigQueryRepository.fetchByProductGroupIds(productGroupIds).stream()
                .map(productGroupNameMapper::toDomain)
                .toList();
    }


}
