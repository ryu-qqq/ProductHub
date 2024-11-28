package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.storage.db.core.product.group.ProductGroupNameConfigEntity;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExternalProductBatchInsertData {

    private final List<ExternalProductEntity> externalProductEntities = new ArrayList<>();
    private final Set<ProductGroupNameConfigEntity> productGroupNameConfigEntities = new HashSet<>();

    public void addExternalProductEntity(ExternalProductEntity externalProductEntity) {
        externalProductEntities.add(externalProductEntity);
    }

    public void addProductGroupNameConfigEntity(ProductGroupNameConfigEntity productGroupNameConfigEntity) {
        productGroupNameConfigEntities.add(productGroupNameConfigEntity);
    }

    public List<ExternalProductEntity> getExternalProductEntities() {
        return externalProductEntities;
    }

    public Set<ProductGroupNameConfigEntity> getProductGroupNameConfigEntities() {
        return productGroupNameConfigEntities;
    }

}
