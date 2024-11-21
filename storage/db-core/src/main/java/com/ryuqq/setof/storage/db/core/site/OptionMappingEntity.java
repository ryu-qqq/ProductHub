package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "OPTION_MAPPING")
@Entity
public class OptionMappingEntity extends BaseEntity {

    @Column(name = "OPTION_DETAIL_ID", nullable = false)
    private Long optionDetailId;

    @Column(name = "MAPPING_TYPE", nullable = false, length = 50)
    private String mappingType;

    @Column(name = "REFERENCE_ID", nullable = false)
    private Long referenceId;

    @Column(name = "ACTIVE_YN", nullable = false)
    private boolean activeYn = false;

    @Column(name = "MAPPING_NOTES", columnDefinition = "TEXT")
    private String mappingNotes;

    public OptionMappingEntity() {}

    public OptionMappingEntity(Long optionDetailId, String mappingType, Long referenceId, boolean activeYn, String mappingNotes) {
        this.optionDetailId = optionDetailId;
        this.mappingType = mappingType;
        this.referenceId = referenceId;
        this.activeYn = activeYn;
        this.mappingNotes = mappingNotes;
    }
}
