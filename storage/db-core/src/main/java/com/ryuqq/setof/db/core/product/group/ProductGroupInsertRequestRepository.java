package com.ryuqq.setof.storage.db.core.product.group;


import java.util.List;

public interface ProductGroupInsertRequestRepository {
    void save(ProductGroupInsertRequestEntity productGroupInsertRequestEntity);
    void saveAll(List<ProductGroupInsertRequestEntity> productGroupInsertRequestEntities);
}

