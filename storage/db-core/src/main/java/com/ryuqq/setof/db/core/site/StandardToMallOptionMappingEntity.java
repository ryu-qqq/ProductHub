package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "STANDARD_TO_MALL_OPTION_MAPPING")
@Entity
public class StandardToMallOptionMappingEntity extends BaseEntity {

    @Column(name = "STANDARD_SIZE_ID", nullable = false)
    private Long standardSizeId;

    @Column(name = "MALL_CATEGORY_OPTION_ID", nullable = false)
    private Long mallCategoryOptionId;

    public StandardToMallOptionMappingEntity() {}

    public StandardToMallOptionMappingEntity(Long standardSizeId, Long mallCategoryOptionId) {
        this.standardSizeId = standardSizeId;
        this.mallCategoryOptionId = mallCategoryOptionId;
    }
}
