package com.ryuqq.setof.storage.db.core.product.group;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupJpaService implements ProductGroupPersistenceService {

    private final ProductGroupJpaRepository productGroupJpaRepository;
    private final ProductGroupJdbcRepository productGroupJdbcRepository;


    public ProductGroupJpaService(ProductGroupJpaRepository productGroupJpaRepository, ProductGroupJdbcRepository productGroupJdbcRepository) {
        this.productGroupJpaRepository = productGroupJpaRepository;
        this.productGroupJdbcRepository = productGroupJdbcRepository;
    }

    @Override
    public long insert(ProductGroupEntity productGroupEntity) {
        return productGroupJpaRepository.save(productGroupEntity).getId();
    }

    @Override
    public void insertAll(List<ProductGroupEntity> ProductGroupEntities) {
        productGroupJdbcRepository.batchInsertProductGroups(ProductGroupEntities);
    }

    @Override
    public void update(ProductGroupEntity productDeliveryEntity) {
        updateAll(List.of(productDeliveryEntity));
    }


    @Override
    public void updateAll(List<ProductGroupEntity> ProductGroupEntities) {
        int[] rowsAffected = productGroupJdbcRepository.batchUpdateProductGroups(ProductGroupEntities);
        if(rowsAffected.length != ProductGroupEntities.size()) {

        }
    }


}
