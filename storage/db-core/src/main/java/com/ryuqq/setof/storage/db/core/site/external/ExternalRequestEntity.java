package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.EntityType;
import com.ryuqq.setof.enums.core.RequestType;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;


@Table(name = "EXTERNAL_REQUEST")
@Entity
public class ExternalRequestEntity extends BaseEntity {

    @Column(name = "TRANSACTION_ID", nullable = false)
    private String transactionId;

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

    @Column(name = "REQUEST_BY", nullable = false)
    private String requestBody;

    protected ExternalRequestEntity() {}

    public ExternalRequestEntity(String transactionId, RequestType requestType, long siteId, EntityType entityType, long entityId, SyncStatus status, String requestBody) {
        this.transactionId = transactionId;
        this.requestType = requestType;
        this.siteId = siteId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.status = status;
        this.requestBody = requestBody;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public long getSiteId() {
        return siteId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public long getEntityId() {
        return entityId;
    }

    public SyncStatus getStatus() {
        return status;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
