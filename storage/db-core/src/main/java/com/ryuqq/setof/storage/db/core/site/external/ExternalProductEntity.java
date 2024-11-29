package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "PRODUCT_NAME", nullable = true)
    private String productName;

    @Column(name = "REGULAR_PRICE", nullable = true)
    private BigDecimal regularPrice;

    @Column(name = "CURRENT_PRICE", nullable = true)
    private BigDecimal currentPrice;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private SyncStatus status;

    @Column(name = "SOLD_OUT_YN", nullable = false)
    private boolean soldOutYn;

    @Column(name = "DISPLAY_YN",  nullable = false)
    private boolean displayYn;

    @Column(name = "LAST_SYNC_TIME",  nullable = false)
    private LocalDateTime lastSyncTime;

    protected ExternalProductEntity() {}

    public ExternalProductEntity(long siteId, long productGroupId, long policyId, String externalProductId, String productName, BigDecimal regularPrice, BigDecimal currentPrice, SyncStatus status, boolean soldOutYn, boolean displayYn) {
        this.siteId = siteId;
        this.productGroupId = productGroupId;
        this.policyId = policyId;
        this.externalProductId = externalProductId;
        this.productName = productName;
        this.regularPrice = regularPrice;
        this.currentPrice = currentPrice;
        this.status = status;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.lastSyncTime = LocalDateTime.now();
    }

    public static ExternalProductEntity toWaitingStatusEntity(long siteId, long productGroupId, long policyId){
        return new ExternalProductEntity(
                siteId,
                productGroupId,
                policyId,
                "",
                "",
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                SyncStatus.WAITING,
                false,
                true
        );
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

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public SyncStatus getStatus() {
        return status;
    }

    public boolean isSoldOutYn() {
        return soldOutYn;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }

    public LocalDateTime getLastSyncTime() {
        return lastSyncTime;
    }
}
