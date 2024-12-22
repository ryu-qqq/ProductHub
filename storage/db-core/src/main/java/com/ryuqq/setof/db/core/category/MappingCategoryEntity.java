package com.ryuqq.setof.storage.db.core.category;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "MAPPING_CATEGORY")
@Entity
public class MappingCategoryEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "SITE_CATEGORY_ID", nullable = false)
    private String siteCategoryId;

    @Column(name = "SITE_CATEGORY_EXTRA_ID", nullable = true)
    private String siteCategoryExtraId;

    @Column(name = "DESCRIPTION", length = 100, nullable = true)
    private String description;

    @Column(name = "INTERNAL_CATEGORY_ID", nullable = false)
    private long internalCategoryId;


    protected MappingCategoryEntity() {}

    public MappingCategoryEntity(long siteId, String siteCategoryId, String siteCategoryExtraId, String description, long internalCategoryId) {
        this.siteId = siteId;
        this.siteCategoryId = siteCategoryId;
        this.siteCategoryExtraId = siteCategoryExtraId;
        this.description = description;
        this.internalCategoryId = internalCategoryId;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getSiteCategoryId() {
        return siteCategoryId;
    }

    public String getSiteCategoryExtraId() {
        return siteCategoryExtraId;
    }

    public String getDescription() {
        return description;
    }

    public long getInternalCategoryId() {
        return internalCategoryId;
    }
}
