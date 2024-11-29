package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.dto.ProductStyleCodeDto;

import java.util.List;

public interface ProductGroupPersistenceRepository {
    long insert(ProductGroupEntity productGroupEntity);
    void insertAll(List<ProductGroupEntity> ProductGroupEntities);

    void update(ProductGroupEntity productDeliveryEntity);
    void updateAll(List<ProductGroupEntity> ProductGroupEntities);

    void updateProductStatus(long productGroupId, ProductStatus productStatus);
    void updatesProductStatus(List<Long> productGroupIds, ProductStatus productStatus);

    void updateStyleCodes(List<ProductStyleCodeDto> productStyleCodes);

}
