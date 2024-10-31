package com.ryuqq.setof.storage.db.core.brand;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "MAPPING_BRAND")
@Entity
public class MappingBrandEntity extends BaseEntity {

    private long siteId;
    private String siteBrandId;
    private long internalBrandId;

    protected MappingBrandEntity() {}

    public MappingBrandEntity(long siteId, String siteBrandId, long internalBrandId) {
        this.siteId = siteId;
        this.siteBrandId = siteBrandId;
        this.internalBrandId = internalBrandId;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getSiteBrandId() {
        return siteBrandId;
    }

    public long getInternalBrandId() {
        return internalBrandId;
    }
}
