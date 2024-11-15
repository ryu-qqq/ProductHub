package com.ryuqq.setof.storage.db.core.product.description;

import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailDescriptionJpaService implements ProductDetailDescriptionPersistenceService{

    private final ProductDetailDescriptionJpaRepository productDetailDescriptionJpaRepository;
    private final ProductDetailDescriptionJdbcRepository productDetailDescriptionJdbcRepository;

    public ProductDetailDescriptionJpaService(ProductDetailDescriptionJpaRepository productDetailDescriptionJpaRepository, ProductDetailDescriptionJdbcRepository productDetailDescriptionJdbcRepository) {
        this.productDetailDescriptionJpaRepository = productDetailDescriptionJpaRepository;
        this.productDetailDescriptionJdbcRepository = productDetailDescriptionJdbcRepository;
    }

    @Override
    public void insert(ProductDetailDescriptionEntity productDetailDescriptionEntity) {
        productDetailDescriptionJpaRepository.save(productDetailDescriptionEntity);
    }

    @Override
    public void insertAll(List<ProductDetailDescriptionEntity> ProductDetailDescriptionEntities) {
        productDetailDescriptionJdbcRepository.batchInsertProductDetailDescriptions(ProductDetailDescriptionEntities);
    }

    @Override
    public void update(ProductDetailDescriptionEntity productDetailDescriptionEntity) {
        updateAll(List.of(productDetailDescriptionEntity));
    }

    @Override
    public void updateAll(List<ProductDetailDescriptionEntity> ProductDetailDescriptionEntities) {
        int[] rowsAffected = productDetailDescriptionJdbcRepository.batchUpdateProductDetailDescriptions(ProductDetailDescriptionEntities);
        if(rowsAffected.length != ProductDetailDescriptionEntities.size()) {

        }
    }


}
