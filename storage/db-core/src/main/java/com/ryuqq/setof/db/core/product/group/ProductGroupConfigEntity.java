package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT_GROUP_CONFIG")
public class ProductGroupConfigEntity extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Column(name = "ACTIVE_YN", nullable = false)
    private boolean activeYn = true;

    protected ProductGroupConfigEntity() {}

    public ProductGroupConfigEntity(long productGroupId, boolean activeYn) {
        this.productGroupId = productGroupId;
        this.activeYn = activeYn;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public boolean isActiveYn() {
        return activeYn;
    }
}
