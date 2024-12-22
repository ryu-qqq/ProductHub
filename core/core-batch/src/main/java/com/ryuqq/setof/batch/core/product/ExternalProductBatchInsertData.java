package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.db.core.product.group.ProductGroupNameConfigEntity;
import com.ryuqq.setof.db.core.site.external.ExternalProductGroupEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExternalProductBatchInsertData {

    private final List<ExternalProductGroupEntity> externalProductEntities = new ArrayList<>();
    private final Set<ProductGroupNameConfigEntity> productGroupNameConfigEntities = new HashSet<>();

    public void addExternalProductEntity(ExternalProductGroupEntity externalProductGroupEntity) {
        externalProductEntities.add(externalProductGroupEntity);
    }

    public void addProductGroupNameConfigEntity(ProductGroupNameConfigEntity productGroupNameConfigEntity) {
        productGroupNameConfigEntities.add(productGroupNameConfigEntity);
    }

    public List<ExternalProductGroupEntity> getExternalProductEntities() {
        return externalProductEntities;
    }

    public Set<ProductGroupNameConfigEntity> getProductGroupNameConfigEntities() {
        return productGroupNameConfigEntities;
    }

}
