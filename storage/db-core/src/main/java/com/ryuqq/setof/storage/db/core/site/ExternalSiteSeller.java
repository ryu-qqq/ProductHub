package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;


@Table(name = "EXTERNAL_SITE_SELLER")
@Entity
public class ExternalSiteSeller extends BaseEntity {

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

    protected ExternalSiteSeller() {}

    public ExternalSiteSeller(long siteId, long sellerId, boolean activeStatus, LocalDateTime registrationStartTime, LocalDateTime registrationEndTime) {
        this.siteId = siteId;
        this.sellerId = sellerId;
        this.activeStatus = activeStatus;
        this.registrationStartTime = registrationStartTime;
        this.registrationEndTime = registrationEndTime;
    }
}
