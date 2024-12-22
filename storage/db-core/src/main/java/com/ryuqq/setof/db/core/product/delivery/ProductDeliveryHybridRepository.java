package com.ryuqq.setof.storage.db.core.product.delivery;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDeliveryHybridRepository implements ProductDeliveryPersistenceRepository {

    private final ProductDeliveryJpaRepository productDeliveryJpaRepository;
    private final ProductDeliveryJdbcRepository productDeliveryJdbcRepository;

    public ProductDeliveryHybridRepository(ProductDeliveryJpaRepository productDeliveryJpaRepository, ProductDeliveryJdbcRepository productDeliveryJdbcRepository) {
        this.productDeliveryJpaRepository = productDeliveryJpaRepository;
        this.productDeliveryJdbcRepository = productDeliveryJdbcRepository;
    }

    @Override
    public void saveProductDelivery(ProductDeliveryEntity productDeliveryEntity) {
        productDeliveryJpaRepository.save(productDeliveryEntity);
    }

    @Override
    public void saveAllProductDelivery(List<ProductDeliveryEntity> productDeliveryEntities) {
        productDeliveryJdbcRepository.batchInsertProductDeliveries(productDeliveryEntities);
    }

    @Override
    public void updateProductDelivery(ProductDeliveryEntity productDeliveryEntity) {
        updateAllProductDelivery(List.of(productDeliveryEntity));
    }

    @Override
    public void updateAllProductDelivery(List<ProductDeliveryEntity> productDeliveryEntities) {
        int[] rowsAffected = productDeliveryJdbcRepository.batchUpdateProductDeliveries(productDeliveryEntities);
        if(rowsAffected.length != productDeliveryEntities.size()) {

        }
    }


}
