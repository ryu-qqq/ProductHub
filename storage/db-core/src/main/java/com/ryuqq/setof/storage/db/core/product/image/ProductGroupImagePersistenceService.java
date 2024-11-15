package com.ryuqq.setof.storage.db.core.product.image;

import java.util.List;

public interface ProductGroupImagePersistenceService {

    void insert(ProductGroupImageEntity productGroupImageEntity);
    void insertAll(List<ProductGroupImageEntity> productGroupImageEntities);
    void updateAll(List<ProductGroupImageEntity> productGroupImageEntities);

}
