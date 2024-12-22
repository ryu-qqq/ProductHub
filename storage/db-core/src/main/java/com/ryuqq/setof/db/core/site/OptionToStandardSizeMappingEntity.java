package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "OPTION_TO_STANDARD_SIZE_MAPPING")
@Entity
public class OptionToStandardSizeMappingEntity extends BaseEntity {

    @Column(name = "OPTION_DETAIL_ID", nullable = false)
    private Long optionDetailId;

    @Column(name = "STANDARD_SIZE_ID", nullable = false)
    private Long standardSizeId;

    @Column(name = "MAPPING_NOTES", columnDefinition = "TEXT")
    private String mappingNotes;

    @Column(name = "ACTIVE_YN", nullable = false)
    private boolean activeYn = false;

    public OptionToStandardSizeMappingEntity() {}

    public OptionToStandardSizeMappingEntity(Long optionDetailId, Long standardSizeId, String mappingNotes, boolean activeYn) {
        this.optionDetailId = optionDetailId;
        this.standardSizeId = standardSizeId;
        this.mappingNotes = mappingNotes;
        this.activeYn = activeYn;
    }
}