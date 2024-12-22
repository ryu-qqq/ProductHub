package com.ryuqq.setof.storage.db.core.product.group;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductGroupInsertRequestHybridRepository implements ProductGroupInsertRequestRepository{

    private final ProductGroupInsertRequestJpaRepository productGroupInsertRequestJpaRepository;

    public ProductGroupInsertRequestHybridRepository(ProductGroupInsertRequestJpaRepository productGroupInsertRequestJpaRepository) {
        this.productGroupInsertRequestJpaRepository = productGroupInsertRequestJpaRepository;
    }

    @Override
    public void save(ProductGroupInsertRequestEntity productGroupInsertRequestEntity) {
        productGroupInsertRequestJpaRepository.save(productGroupInsertRequestEntity);
    }

    @Override
    public void saveAll(List<ProductGroupInsertRequestEntity> productGroupInsertRequestEntities) {
        productGroupInsertRequestJpaRepository.saveAll(productGroupInsertRequestEntities);
    }



}
