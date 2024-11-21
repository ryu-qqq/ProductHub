package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Table(name = "EXTERNAL_PRODUCT_POLICY")
@Entity
public class ExternalProductPolicyEntity extends BaseEntity {

    @Column(name = "POLICY_ID", nullable = false)
    private long policyId;

    @Column(name = "COUNTRY_CODE", nullable = false)
    private Origin countryCode;

    @Column(name = "TRANSLATED_NEEDED", nullable = false)
    private boolean translated;

    protected ExternalProductPolicyEntity() {}

    public ExternalProductPolicyEntity(long policyId, Origin countryCode, boolean translated) {
        this.policyId = policyId;
        this.countryCode = countryCode;
        this.translated = translated;
    }
}
