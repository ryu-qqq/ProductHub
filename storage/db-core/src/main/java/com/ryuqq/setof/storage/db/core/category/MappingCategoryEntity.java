package com.ryuqq.setof.storage.db.core.category;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "MAPPING_CATEGORY")
@Entity
public class MappingCategoryEntity extends BaseEntity {

    private long siteId;
    private String siteCategoryId;
    private long internalCategoryId;

    protected MappingCategoryEntity() {}

    public MappingCategoryEntity(long siteId, String siteCategoryId, long internalCategoryId) {
        this.siteId = siteId;
        this.siteCategoryId = siteCategoryId;
        this.internalCategoryId = internalCategoryId;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getSiteCategoryId() {
        return siteCategoryId;
    }

    public long getInternalCategoryId() {
        return internalCategoryId;
    }
}
