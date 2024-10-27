package com.ryuqq.setof.storage.db.core.product.description;

import org.springframework.stereotype.Service;

@Service
public class ProductDetailDescriptionJpaService implements ProductDetailDescriptionPersistenceService{

    private final ProductDetailDescriptionJpaRepository productDetailDescriptionJpaRepository;

    public ProductDetailDescriptionJpaService(ProductDetailDescriptionJpaRepository productDetailDescriptionJpaRepository) {
        this.productDetailDescriptionJpaRepository = productDetailDescriptionJpaRepository;
    }

    @Override
    public void insert(ProductDetailDescriptionEntity productDetailDescriptionEntity) {
        productDetailDescriptionJpaRepository.save(productDetailDescriptionEntity);
    }
}
