package com.ryuqq.setof.storage.db.core.product.group;

import org.springframework.stereotype.Service;

@Service
public class ProductGroupJpaService implements ProductGroupPersistenceService {

    private final ProductGroupJpaRepository productGroupJpaRepository;

    public ProductGroupJpaService(ProductGroupJpaRepository productGroupJpaRepository) {
        this.productGroupJpaRepository = productGroupJpaRepository;
    }

    @Override
    public long insert(ProductGroupEntity productGroupEntity) {
        return productGroupJpaRepository.save(productGroupEntity).getId();
    }
}
