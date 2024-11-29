package com.ryuqq.setof.storage.db.core.product.description;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDetailDescriptionHybridRepository implements ProductDetailDescriptionPersistenceRepository {

    private final ProductDetailDescriptionJpaRepository productDetailDescriptionJpaRepository;
    private final ProductDetailDescriptionJdbcRepository productDetailDescriptionJdbcRepository;

    public ProductDetailDescriptionHybridRepository(ProductDetailDescriptionJpaRepository productDetailDescriptionJpaRepository, ProductDetailDescriptionJdbcRepository productDetailDescriptionJdbcRepository) {
        this.productDetailDescriptionJpaRepository = productDetailDescriptionJpaRepository;
        this.productDetailDescriptionJdbcRepository = productDetailDescriptionJdbcRepository;
    }

    @Override
    public void saveAllProductDetailDescription(ProductDetailDescriptionEntity productDetailDescriptionEntity) {
        productDetailDescriptionJpaRepository.save(productDetailDescriptionEntity);
    }

    @Override
    public void saveAllProductDetailDescription(List<ProductDetailDescriptionEntity> ProductDetailDescriptionEntities) {
        productDetailDescriptionJdbcRepository.batchInsertProductDetailDescriptions(ProductDetailDescriptionEntities);
    }

    @Override
    public void updateProductDelivery(ProductDetailDescriptionEntity productDetailDescriptionEntity) {
        updateAllProductDelivery(List.of(productDetailDescriptionEntity));
    }

    @Override
    public void updateAllProductDelivery(List<ProductDetailDescriptionEntity> ProductDetailDescriptionEntities) {
        int[] rowsAffected = productDetailDescriptionJdbcRepository.batchUpdateProductDetailDescriptions(ProductDetailDescriptionEntities);
        if(rowsAffected.length != ProductDetailDescriptionEntities.size()) {

        }
    }


}
