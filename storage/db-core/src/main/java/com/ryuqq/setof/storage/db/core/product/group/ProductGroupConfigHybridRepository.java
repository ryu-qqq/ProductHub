package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupNameConfigDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupConfigHybridRepository implements ProductGroupConfigPersistenceRepository {

    private final ProductGroupConfigJdbcRepository productGroupConfigJdbcRepository;
    private final ProductGroupNameConfigJdbcRepository productGroupNameConfigJdbcRepository;

    public ProductGroupConfigHybridRepository(ProductGroupConfigJdbcRepository productGroupConfigJdbcRepository, ProductGroupNameConfigJdbcRepository productGroupNameConfigJdbcRepository) {
        this.productGroupConfigJdbcRepository = productGroupConfigJdbcRepository;
        this.productGroupNameConfigJdbcRepository = productGroupNameConfigJdbcRepository;
    }

    @Override
    public void saveAllProductGroupConfigEntities(List<ProductGroupConfigEntity> productGroupConfigEntities) {
        productGroupConfigJdbcRepository.batchInsertProductConfigs(productGroupConfigEntities);
    }

    @Override
    public void saveAllProductGroupNameConfigEntities(List<ProductGroupNameConfigEntity> productGroupNameConfigEntities) {
        productGroupNameConfigJdbcRepository.batchInsertProductConfigs(productGroupNameConfigEntities);
    }

    @Override
    public void updateAllProductGroupNameConfigEntities(List<ProductGroupNameConfigDto> productGroupNameConfigDtos) {
        productGroupNameConfigJdbcRepository.batchUpdateProductGroupNameConfigs(productGroupNameConfigDtos);
    }

}
