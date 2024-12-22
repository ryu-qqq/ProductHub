package com.ryuqq.setof.storage.db.core.product.description;

import java.util.List;

public interface ProductDetailDescriptionPersistenceRepository {

    void saveAllProductDetailDescription(ProductDetailDescriptionEntity productDetailDescriptionEntity);
    void saveAllProductDetailDescription(List<ProductDetailDescriptionEntity> ProductDetailDescriptionEntities);
    void updateProductDelivery(ProductDetailDescriptionEntity productDetailDescriptionEntity);
    void updateAllProductDelivery(List<ProductDetailDescriptionEntity> ProductDetailDescriptionEntities);
}
