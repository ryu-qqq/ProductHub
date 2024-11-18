package com.ryuqq.setof.storage.db.core.product.option;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductJpaService implements ProductPersistenceService{

    private final ProductJpaRepository productJpaRepository;
    private final ProductJdbcRepository productJdbcRepository;

    public ProductJpaService(ProductJpaRepository productJpaRepository, ProductJdbcRepository productJdbcRepository) {
        this.productJpaRepository = productJpaRepository;
        this.productJdbcRepository = productJdbcRepository;
    }

    @Override
    public long insert(ProductEntity productEntity) {
        return productJpaRepository.save(productEntity).getId();
    }

    @Override
    public void updateAll(List<ProductEntity> productEntities) {
        productJdbcRepository.batchUpdateProducts(productEntities);
    }

    @Override
    public void deleteAll(List<Long> productIds) {
        productJdbcRepository.softDeleteAll(productIds);
    }


}
