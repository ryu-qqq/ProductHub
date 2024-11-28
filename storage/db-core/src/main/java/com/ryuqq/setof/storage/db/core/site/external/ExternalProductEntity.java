package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "EXTERNAL_PRODUCT")
@Entity
public class ExternalProductEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Column(name = "POLICY_ID", nullable = false)
    private long policyId;

    @Column(name = "EXTERNAL_PRODUCT_ID", nullable = true)
    private String externalProductId;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private SyncStatus status;

    protected ExternalProductEntity() {}

    public ExternalProductEntity(long siteId, long productGroupId, long policyId) {
        this.siteId = siteId;
        this.productGroupId = productGroupId;
        this.policyId = policyId;
        this.status = SyncStatus.WAITING;
    }

    public ExternalProductEntity(long siteId, long productGroupId, long policyId, String externalProductId, String productName, SyncStatus status) {
        this.siteId = siteId;
        this.productGroupId = productGroupId;
        this.policyId = policyId;
        this.externalProductId = externalProductId;
        this.productName = productName;
        this.status = status;
    }

    public long getSiteId() {
        return siteId;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getPolicyId() {
        return policyId;
    }

    public String getExternalProductId() {
        return externalProductId;
    }

    public String getProductName() {
        return productName;
    }

    public SyncStatus getStatus() {
        return status;
    }
}
