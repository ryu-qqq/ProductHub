package com.ryuqq.setof.storage.db.core.product.option;

import java.util.List;

public interface ProductPersistenceService {

    long insert(ProductEntity productEntity);

    void updateAll(List<ProductEntity> productEntities);
    void deleteAll(List<Long> productIds);
}
