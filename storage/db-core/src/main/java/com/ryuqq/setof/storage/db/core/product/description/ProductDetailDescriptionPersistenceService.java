package com.ryuqq.setof.storage.db.core.product.description;

import java.util.List;

public interface ProductDetailDescriptionPersistenceService {

    void insert(ProductDetailDescriptionEntity productDetailDescriptionEntity);
    void insertAll(List<ProductDetailDescriptionEntity> ProductDetailDescriptionEntities);
    void update(ProductDetailDescriptionEntity productDetailDescriptionEntity);
    void updateAll(List<ProductDetailDescriptionEntity> ProductDetailDescriptionEntities);
}
