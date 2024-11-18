package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.enums.core.ProductStatus;
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
    public void update(ProductGroupEntity productGroupEntity) {
        updateAll(List.of(productGroupEntity));
    }


    @Override
    public void updateAll(List<ProductGroupEntity> ProductGroupEntities) {
        int[] rowsAffected = productGroupJdbcRepository.batchUpdateProductGroups(ProductGroupEntities);
        if(rowsAffected.length != ProductGroupEntities.size()) {

        }
    }

    @Override
    public void updateProductStatus(long productGroupId, ProductStatus productStatus) {
        productGroupJdbcRepository.updatesProductStatus(List.of(productGroupId), productStatus);
    }

    @Override
    public void updatesProductStatus(List<Long> productGroupIds, ProductStatus productStatus) {
        productGroupJdbcRepository.updatesProductStatus(productGroupIds, productStatus);
    }

}
