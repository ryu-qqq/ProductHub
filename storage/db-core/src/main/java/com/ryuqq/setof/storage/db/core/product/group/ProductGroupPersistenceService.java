package com.ryuqq.setof.storage.db.core.product.group;

import java.util.List;

public interface ProductGroupPersistenceService {
    long insert(ProductGroupEntity productGroupEntity);
    void insertAll(List<ProductGroupEntity> ProductGroupEntities);

    void update(ProductGroupEntity productDeliveryEntity);
    void updateAll(List<ProductGroupEntity> ProductGroupEntities);
}
