package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "OPTION_TO_MALL_CATEGORY_MAPPING")
@Entity
public class OptionToMallCategoryMappingEntity extends BaseEntity {

    @Column(name = "OPTION_DETAIL_ID", nullable = false)
    private Long optionDetailId;

    @Column(name = "MALL_CATEGORY_OPTION_ID", nullable = false)
    private Long mallCategoryOptionId;

    @Column(name = "MAPPING_NOTES", columnDefinition = "TEXT")
    private String mappingNotes;

    @Column(name = "ACTIVE_YN", nullable = false)
    private boolean activeYn = false;

    public OptionToMallCategoryMappingEntity() {}

    public OptionToMallCategoryMappingEntity(Long optionDetailId, Long mallCategoryOptionId, String mappingNotes, boolean activeYn) {
        this.optionDetailId = optionDetailId;
        this.mallCategoryOptionId = mallCategoryOptionId;
        this.mappingNotes = mappingNotes;
        this.activeYn = activeYn;
    }
}
