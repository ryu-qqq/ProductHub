package com.ryuqq.setof.storage.db.core.brand;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "BRAND")
@Entity
public class BrandEntity extends BaseEntity {

    @Column(name = "BRAND_NAME", nullable = false, length = 50)
    private String brandName;

    @Column(name = "BRAND_ICON_IMAGE_URL", nullable = true, length = 255)
    private String brandIconImageUrl;

    @Column(name = "DISPLAY_YN", nullable = false)
    private boolean displayYn;

    protected BrandEntity() {
    }

    public BrandEntity(String brandName, String brandIconImageUrl, boolean displayYn) {
        this.brandName = brandName;
        this.brandIconImageUrl = brandIconImageUrl;
        this.displayYn = displayYn;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getBrandIconImageUrl() {
        return brandIconImageUrl;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }
}
