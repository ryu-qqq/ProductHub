package com.ryuqq.setof.storage.db.core.product.image;

import java.util.List;

public interface ProductGroupImagePersistenceRepository {

    void saveProductGroupImage(ProductGroupImageEntity productGroupImageEntity);
    void saveAllProductGroupImage(List<ProductGroupImageEntity> productGroupImageEntities);
    void updateAllProductGroupImage(List<ProductGroupImageEntity> productGroupImageEntities);

}
