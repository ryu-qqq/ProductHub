package com.ryuqq.setof.storage.db.core.product.option;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCommandFacade implements ProductPersistenceRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductJdbcRepository productJdbcRepository;

    public ProductCommandFacade(ProductJpaRepository productJpaRepository, ProductJdbcRepository productJdbcRepository) {
        this.productJpaRepository = productJpaRepository;
        this.productJdbcRepository = productJdbcRepository;
    }

    @Override
    public long insertProduct(ProductEntity productEntity) {
        return productJpaRepository.save(productEntity).getId();
    }

    @Override
    public void updateAllProduct(List<ProductEntity> productEntities) {
        productJdbcRepository.batchUpdateProducts(productEntities);
    }

    @Override
    public void deleteAllProduct(List<Long> productIds) {
        productJdbcRepository.softDeleteAll(productIds);
    }


}
