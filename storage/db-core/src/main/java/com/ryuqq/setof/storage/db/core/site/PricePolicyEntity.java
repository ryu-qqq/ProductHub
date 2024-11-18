package com.ryuqq.setof.storage.db.core.site;


import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "PRICE_POLICY")
@Entity
public class PricePolicyEntity extends BaseEntity {


    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "NAME", length = 255, nullable = true)
    private String name;

    @Column(name = "DESCRIPTION", length = 500, nullable = true)
    private String description;

    protected PricePolicyEntity() {}

    public PricePolicyEntity(long siteId, String name, String description) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
    }
}
