package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Table(name = "EXTERNAL_POLICY")
@Entity
public class ExternalPolicyEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "NAME", length = 255, nullable = true)
    private String name;

    @Column(name = "DESCRIPTION", length = 500, nullable = true)
    private String description;

    @Column(name = "ACTIVE_YN", nullable = false)
    private boolean activeYn;

    protected ExternalPolicyEntity() {}

    public ExternalPolicyEntity(long siteId, String name, String description, boolean activeYn) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.activeYn = activeYn;
    }


}
