package com.ryuqq.setof.storage.db.core.product.option.mapping;

import org.springframework.stereotype.Service;

@Service
public class ProductOptionJpaService implements ProductOptionPersistenceService {

    private final ProductOptionJpaRepository productOptionJpaRepository;


    public ProductOptionJpaService(ProductOptionJpaRepository productOptionJpaRepository) {
        this.productOptionJpaRepository = productOptionJpaRepository;
    }

    @Override
    public void insert(ProductOptionEntity productOptionEntity) {
        productOptionJpaRepository.save(productOptionEntity);
    }
}
