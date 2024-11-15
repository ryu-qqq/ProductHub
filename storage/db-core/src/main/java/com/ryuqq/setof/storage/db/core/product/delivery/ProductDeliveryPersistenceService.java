package com.ryuqq.setof.storage.db.core.product.delivery;

import java.util.List;

public interface ProductDeliveryPersistenceService {

    void insert(ProductDeliveryEntity productDelivery);
    void insertAll(List<ProductDeliveryEntity> productDeliveryEntities);
    void update(ProductDeliveryEntity productDeliveryEntity);
    void updateAll(List<ProductDeliveryEntity> productDeliveryEntities);

}
