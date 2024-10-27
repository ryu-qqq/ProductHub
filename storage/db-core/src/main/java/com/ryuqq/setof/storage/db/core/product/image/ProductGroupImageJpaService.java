package com.ryuqq.setof.storage.db.core.product.image;

import org.springframework.stereotype.Service;

@Service
public class ProductGroupImageJpaService implements ProductGroupImagePersistenceService{

    private final ProductGroupImageJpaRepository productGroupImageJpaRepository;

    protected ProductGroupImageJpaService(ProductGroupImageJpaRepository productGroupImageJpaRepository) {
        this.productGroupImageJpaRepository = productGroupImageJpaRepository;
    }

    @Override
    public void insert(ProductGroupImageEntity productGroupImageEntity) {
        productGroupImageJpaRepository.save(productGroupImageEntity);
    }
}
