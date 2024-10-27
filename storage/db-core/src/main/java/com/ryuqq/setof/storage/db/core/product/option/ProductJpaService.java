package com.ryuqq.setof.storage.db.core.product.option;

import org.springframework.stereotype.Service;

@Service
public class ProductJpaService implements ProductPersistenceService{

    private final ProductJpaRepository productJpaRepository;

    public ProductJpaService(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public long insert(ProductEntity productEntity) {
        return productJpaRepository.save(productEntity).getId();
    }

}
