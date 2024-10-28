package com.ryuqq.setof.storage.db.core.product.group;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupQueryDslQueryService implements ProductGroupQueryService {

    private final ProductGroupJpaRepository productGroupJpaRepository;

    public ProductGroupQueryDslQueryService(ProductGroupJpaRepository productGroupJpaRepository) {
        this.productGroupJpaRepository = productGroupJpaRepository;
    }


    @Override
    public List<ProductGroupEntity> findAll() {
        return productGroupJpaRepository.findAll();
    }
}
