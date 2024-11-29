package com.ryuqq.setof.storage.db.core.product.option;

import java.util.List;

public interface ProductPersistenceRepository {

    long insertProduct(ProductEntity productEntity);
    void updateAllProduct(List<ProductEntity> productEntities);
    void deleteAllProduct(List<Long> productIds);
}
