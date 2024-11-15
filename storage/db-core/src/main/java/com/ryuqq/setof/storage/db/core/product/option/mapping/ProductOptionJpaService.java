package com.ryuqq.setof.storage.db.core.product.option.mapping;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOptionJpaService implements ProductOptionPersistenceService {

    private final ProductOptionJpaRepository productOptionJpaRepository;
    private final ProductOptionJdbcRepository productOptionJdbcRepository;


    public ProductOptionJpaService(ProductOptionJpaRepository productOptionJpaRepository, ProductOptionJdbcRepository productOptionJdbcRepository) {
        this.productOptionJpaRepository = productOptionJpaRepository;
        this.productOptionJdbcRepository = productOptionJdbcRepository;
    }

    @Override
    public void insert(ProductOptionEntity productOptionEntity) {
        productOptionJpaRepository.save(productOptionEntity);
    }

    @Override
    public void insertAll(List<ProductOptionEntity> productOptionEntities) {
        productOptionJdbcRepository.batchInsertProductOptions(productOptionEntities);
    }

    @Override
    public void updateAll(List<ProductOptionEntity> productOptionEntities) {
        productOptionJdbcRepository.batchUpdateProductOptions(productOptionEntities);

    }

    @Override
    public void deleteAll(List<Long> productIds) {
        productOptionJdbcRepository.softDeleteAll(productIds);
    }
}
