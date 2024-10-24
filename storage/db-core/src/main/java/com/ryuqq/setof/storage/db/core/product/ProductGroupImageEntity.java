package com.ryuqq.setof.storage.db.core.product;

import com.ryuqq.setof.core.ProductImageType;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "PRODUCT_GROUP_IMAGE")
@Entity
public class ProductGroupImageEntity extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "PRODUCT_GROUP_IMAGE_TYPE")
    private ProductImageType productImageType;

    @Column(name = "IMAGE_URL", length = 255, nullable = false)
    private String imageUrl;

    protected ProductGroupImageEntity() {}

    public ProductGroupImageEntity(long productGroupId, ProductImageType productImageType, String imageUrl) {
        this.productGroupId = productGroupId;
        this.productImageType = productImageType;
        this.imageUrl = imageUrl;
    }
}
