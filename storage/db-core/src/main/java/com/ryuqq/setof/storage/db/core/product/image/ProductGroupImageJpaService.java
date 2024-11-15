package com.ryuqq.setof.storage.db.core.product.image;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupImageJpaService implements ProductGroupImagePersistenceService{

    private final ProductGroupImageJpaRepository productGroupImageJpaRepository;
    private final ProductGroupImageJdbcRepository productGroupImageJdbcRepository;

    protected ProductGroupImageJpaService(ProductGroupImageJpaRepository productGroupImageJpaRepository, ProductGroupImageJdbcRepository productGroupImageJdbcRepository) {
        this.productGroupImageJpaRepository = productGroupImageJpaRepository;
        this.productGroupImageJdbcRepository = productGroupImageJdbcRepository;
    }

    @Override
    public void insert(ProductGroupImageEntity productGroupImageEntity) {
        productGroupImageJpaRepository.save(productGroupImageEntity);
    }

    @Override
    public void insertAll(List<ProductGroupImageEntity> productGroupImageEntities) {
        productGroupImageJdbcRepository.batchInsertProductGroupImages(productGroupImageEntities);
    }


    @Override
    public void updateAll(List<ProductGroupImageEntity> productGroupImageEntities) {
        int[] rowsAffected = productGroupImageJdbcRepository.batchUpdateProductGroups(productGroupImageEntities);
        if(rowsAffected.length != productGroupImageEntities.size()) {

        }
    }

}
