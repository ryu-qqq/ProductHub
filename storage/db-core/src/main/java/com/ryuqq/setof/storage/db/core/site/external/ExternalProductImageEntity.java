package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_PRODUCT_IMAGE")
@Entity
public class ExternalProductImageEntity extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_ID", nullable = true)
    private long productGroupId;

    @Column(name = "SITE_ID", nullable = true)
    private long siteId;

    @Column(name = "EXTERNAL_PRODUCT_GROUP_ID", nullable = true)
    private String externalProductGroupId;

    @Column(name = "DISPLAY_ORDER", nullable = false)
    private int displayOrder;

    @Column(name = "IMAGE_URL", length = 255, nullable = false)
    private String imageUrl;

    @Column(name = "ORIGIN_URL", length = 255, nullable = false)
    private String originUrl;

    protected ExternalProductImageEntity() {}

    public ExternalProductImageEntity(long productGroupId, long siteId, String externalProductGroupId, int displayOrder, String imageUrl, String originUrl) {
        this.productGroupId = productGroupId;
        this.siteId = siteId;
        this.externalProductGroupId = externalProductGroupId;
        this.displayOrder = displayOrder;
        this.imageUrl = imageUrl;
        this.originUrl = originUrl;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getExternalProductGroupId() {
        return externalProductGroupId;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOriginUrl() {
        return originUrl;
    }
}
