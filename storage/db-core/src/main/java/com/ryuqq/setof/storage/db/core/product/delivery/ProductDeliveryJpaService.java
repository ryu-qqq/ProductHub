package com.ryuqq.setof.storage.db.core.product.delivery;

import org.springframework.stereotype.Service;

@Service
public class ProductDeliveryJpaService implements ProductDeliveryPersistenceService{

    private final ProductDeliveryJpaRepository productDeliveryJpaRepository;

    public ProductDeliveryJpaService(ProductDeliveryJpaRepository productDeliveryJpaRepository) {
        this.productDeliveryJpaRepository = productDeliveryJpaRepository;
    }

    @Override
    public void insert(ProductDeliveryEntity productDelivery) {
        productDeliveryJpaRepository.save(productDelivery);
    }
}
