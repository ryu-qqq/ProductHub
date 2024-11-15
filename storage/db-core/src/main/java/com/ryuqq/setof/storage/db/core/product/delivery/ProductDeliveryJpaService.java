package com.ryuqq.setof.storage.db.core.product.delivery;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDeliveryJpaService implements ProductDeliveryPersistenceService{

    private final ProductDeliveryJpaRepository productDeliveryJpaRepository;
    private final ProductDeliveryJdbcRepository productDeliveryJdbcRepository;

    public ProductDeliveryJpaService(ProductDeliveryJpaRepository productDeliveryJpaRepository, ProductDeliveryJdbcRepository productDeliveryJdbcRepository) {
        this.productDeliveryJpaRepository = productDeliveryJpaRepository;
        this.productDeliveryJdbcRepository = productDeliveryJdbcRepository;
    }

    @Override
    public void insert(ProductDeliveryEntity productDeliveryEntity) {
        productDeliveryJpaRepository.save(productDeliveryEntity);
    }

    @Override
    public void insertAll(List<ProductDeliveryEntity> productDeliveryEntities) {
        productDeliveryJdbcRepository.batchInsertProductDeliveries(productDeliveryEntities);
    }

    @Override
    public void update(ProductDeliveryEntity productDeliveryEntity) {
        updateAll(List.of(productDeliveryEntity));
    }

    @Override
    public void updateAll(List<ProductDeliveryEntity> productDeliveryEntities) {
        int[] rowsAffected = productDeliveryJdbcRepository.batchUpdateProductDeliveries(productDeliveryEntities);
        if(rowsAffected.length != productDeliveryEntities.size()) {

        }
    }


}
