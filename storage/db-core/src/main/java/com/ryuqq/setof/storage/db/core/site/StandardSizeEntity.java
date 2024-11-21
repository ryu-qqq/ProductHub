package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "STANDARD_SIZE")
@Entity
public class StandardSizeEntity extends BaseEntity {

    @Column(name = "BRAND_ID", nullable = false)
    private Long brandId;

    @Column(name = "REGION_ID", nullable = false)
    private Long regionId;

    @Column(name = "TARGET_GROUP", nullable = false, length = 255)
    private String targetGroup;

    @Column(name = "SIZE_VALUE", nullable = false, length = 50)
    private String sizeValue;

    public StandardSizeEntity() {}

    public StandardSizeEntity(Long brandId, Long regionId, String targetGroup, String sizeValue) {
        this.brandId = brandId;
        this.regionId = regionId;
        this.targetGroup = targetGroup;
        this.sizeValue = sizeValue;
    }
}
