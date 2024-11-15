package com.ryuqq.setof.storage.db.core.product.image;

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


    @Column(name = "ORIGIN_URL", length = 255, nullable = false)
    private String originUrl;

    protected ProductGroupImageEntity() {}

    public ProductGroupImageEntity(long id, long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl, boolean deleteYn) {
        this.id = id;
        this.productGroupId = productGroupId;
        this.productImageType = productImageType;
        this.imageUrl = imageUrl;
        this.originUrl = originUrl;
        this.deleteYn = deleteYn;
    }

    public ProductGroupImageEntity(long productGroupId, ProductImageType productImageType, String imageUrl, String originUrl) {
        this.productGroupId = productGroupId;
        this.productImageType = productImageType;
        this.imageUrl = imageUrl;
        this.originUrl = originUrl;
    }


    protected long getProductGroupId() {
        return productGroupId;
    }

    protected ProductImageType getProductImageType() {
        return productImageType;
    }

    protected String getImageUrl() {
        return imageUrl;
    }

    protected String getOriginUrl() {
        return originUrl;
    }

    public void delete(){
        super.delete();
    }

}
