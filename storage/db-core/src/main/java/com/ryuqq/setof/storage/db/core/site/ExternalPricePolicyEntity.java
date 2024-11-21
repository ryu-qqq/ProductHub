package com.ryuqq.setof.storage.db.core.site;


import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "PRICE_POLICY")
@Entity
public class ExternalPricePolicyEntity extends BaseEntity {

    @Column(name = "POLICY_ID", nullable = false)
    private long policyId;

    @Column(name = "NAME", length = 255, nullable = true)
    private String name;

    @Column(name = "DESCRIPTION", length = 500, nullable = true)
    private String description;

    @Column(name = "CURRENCY", nullable = false)
    private Origin currency;

    @Column(name = "PRIORITY", nullable = false)
    private int priority;

    protected ExternalPricePolicyEntity() {}

    public ExternalPricePolicyEntity(long policyId, String name, String description, Origin currency, int priority) {
        this.policyId = policyId;
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.priority = priority;
    }

}
