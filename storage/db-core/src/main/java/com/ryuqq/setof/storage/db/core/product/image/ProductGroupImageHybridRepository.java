package com.ryuqq.setof.storage.db.core.product.image;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupImageHybridRepository implements ProductGroupImagePersistenceRepository {

    private final ProductGroupImageJpaRepository productGroupImageJpaRepository;
    private final ProductGroupImageJdbcRepository productGroupImageJdbcRepository;

    protected ProductGroupImageHybridRepository(ProductGroupImageJpaRepository productGroupImageJpaRepository, ProductGroupImageJdbcRepository productGroupImageJdbcRepository) {
        this.productGroupImageJpaRepository = productGroupImageJpaRepository;
        this.productGroupImageJdbcRepository = productGroupImageJdbcRepository;
    }

    @Override
    public void saveProductGroupImage(ProductGroupImageEntity productGroupImageEntity) {
        productGroupImageJpaRepository.save(productGroupImageEntity);
    }

    @Override
    public void saveAllProductGroupImage(List<ProductGroupImageEntity> productGroupImageEntities) {
        productGroupImageJdbcRepository.batchInsertProductGroupImages(productGroupImageEntities);
    }


    @Override
    public void updateAllProductGroupImage(List<ProductGroupImageEntity> productGroupImageEntities) {
        int[] rowsAffected = productGroupImageJdbcRepository.batchUpdateProductGroups(productGroupImageEntities);
        if(rowsAffected.length != productGroupImageEntities.size()) {

        }
    }

}
