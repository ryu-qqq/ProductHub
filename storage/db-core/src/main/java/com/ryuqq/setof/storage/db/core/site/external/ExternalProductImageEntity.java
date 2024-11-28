package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "EXTERNAL_PRODUCT_IMAGE")
@Entity
public class ExternalProductImageEntity extends BaseEntity {

    @Column(name = "EXTERNAL_PRODUCT_ID", nullable = true)
    private String externalProductId;

    @Column(name = "DISPLAY_ORDER", nullable = false)
    private int displayOrder;

    @Column(name = "IMAGE_URL", length = 255, nullable = false)
    private String imageUrl;

    @Column(name = "ORIGIN_URL", length = 255, nullable = false)
    private String originUrl;

    protected ExternalProductImageEntity() {}

    public ExternalProductImageEntity(String externalProductId, int displayOrder, String imageUrl, String originUrl) {
        this.externalProductId = externalProductId;
        this.displayOrder = displayOrder;
        this.imageUrl = imageUrl;
        this.originUrl = originUrl;
    }

    public String getExternalProductId() {
        return externalProductId;
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
