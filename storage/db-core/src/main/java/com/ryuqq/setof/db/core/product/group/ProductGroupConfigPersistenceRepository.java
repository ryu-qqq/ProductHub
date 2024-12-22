package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupNameConfigDto;

import java.util.List;

public interface ProductGroupConfigPersistenceRepository {

    void saveAllProductGroupConfigEntities(List<ProductGroupConfigEntity> productGroupConfigEntities);
    void saveAllProductGroupNameConfigEntities(List<ProductGroupNameConfigEntity> productGroupNameConfigEntities);
    void updateAllProductGroupNameConfigEntities(List<ProductGroupNameConfigDto> productGroupNameConfigDtos);

}
