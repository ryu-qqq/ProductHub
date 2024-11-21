package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;


@Table(name = "EXTERNAL_SITE_SELLER")
@Entity
public class ExternalSiteSellerEntity extends BaseEntity {

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "SELLER_ID", nullable = false)
    private long sellerId;

    @Column(name = "ACTIVE_STATUS", nullable = false)
    private boolean activeStatus;

    @Column(name = "REGISTRATION_START_TIME", nullable = false)
    private LocalDateTime registrationStartTime;

    @Column(name = "REGISTRATION_END_TIME", nullable = false)
    private LocalDateTime registrationEndTime;

    protected ExternalSiteSellerEntity() {}

    public ExternalSiteSellerEntity(long siteId, long sellerId) {
        this.siteId = siteId;
        this.sellerId = sellerId;
        this.activeStatus = true;
        this.registrationStartTime = LocalDateTime.now();
        this.registrationEndTime = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    }

    public long getSellerId() {
        return sellerId;
    }
}
