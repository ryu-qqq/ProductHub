package com.ryuqq.setof.storage.db.core.product.option.mapping;

import java.util.List;

public interface ProductOptionPersistenceService {

    void insert(ProductOptionEntity productOptionEntity);
    void insertAll(List<ProductOptionEntity> productOptionEntities);
    void updateAll(List<ProductOptionEntity> productOptionEntities);
    void deleteAll(List<Long> productIds);
}
