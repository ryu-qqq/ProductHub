package com.ryuqq.setof.storage.db.core.product.delivery;

import java.util.List;

public interface ProductDeliveryPersistenceRepository {

    void saveProductDelivery(ProductDeliveryEntity productDelivery);
    void saveAllProductDelivery(List<ProductDeliveryEntity> productDeliveryEntities);
    void updateProductDelivery(ProductDeliveryEntity productDeliveryEntity);
    void updateAllProductDelivery(List<ProductDeliveryEntity> productDeliveryEntities);

}
