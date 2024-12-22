package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "STANDARD_SIZE")
@Entity
public class StandardSizeEntity extends BaseEntity {

    @Column(name = "CATEGORY_ID", nullable = false)
    private Long categoryId;

    @Column(name = "REGION_ID", nullable = false)
    private Long regionId;

    @Column(name = "SIZE_VALUE", nullable = false, length = 50)
    private String sizeValue;

    public StandardSizeEntity() {}

    public StandardSizeEntity(Long categoryId, Long regionId, String sizeValue) {
        this.categoryId = categoryId;
        this.regionId = regionId;
        this.sizeValue = sizeValue;
    }
}
