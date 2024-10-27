package com.ryuqq.setof.storage.db.core.product.description;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT_GROUP_DETAIL_DESCRIPTION")
public class ProductDetailDescriptionEntity extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Lob
    @Column(name = "DETAIL_DESCRIPTION", nullable = false)
    private String detailDescription;

    protected ProductDetailDescriptionEntity() {}

    public ProductDetailDescriptionEntity(long productGroupId, String detailDescription) {
        this.productGroupId = productGroupId;
        this.detailDescription = detailDescription;
    }
}
