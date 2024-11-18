package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.enums.core.*;

import com.ryuqq.setof.enums.core.EntityType;
import com.ryuqq.setof.enums.core.RequestType;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;


@Table(name = "EXTERNAL_REQUEST")
@Entity
public class ExternalRequest extends BaseEntity {

    @Column(name = "TRANSACTION_ID", nullable = false)
    private long transactionId;

    @Column(name = "REQUEST_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Column(name = "SITE_ID", nullable = false)
    private long siteId;

    @Column(name = "ENTITY_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @Column(name = "ENTITY_ID", nullable = false)
    private long entityId;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private SyncStatus status;

    @Column(name = "REQUEST_BY", length = 255, nullable = false)
    private String requestBy;

    protected ExternalRequest() {}

    public ExternalRequest(long transactionId, RequestType requestType, long siteId, EntityType entityType, long entityId, SyncStatus status, String requestBy) {
        this.transactionId = transactionId;
        this.requestType = requestType;
        this.siteId = siteId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.status = status;
        this.requestBy = requestBy;
    }


}
