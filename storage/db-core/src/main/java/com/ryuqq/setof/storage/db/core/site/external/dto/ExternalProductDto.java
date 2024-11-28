package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.SyncStatus;

public class ExternalProductDto {

    private final long id;
    private final long siteId;
    private final long productGroupId;
    private final String externalProductId;
    private final long policyId;
    private final SyncStatus status;

    @QueryProjection
    public ExternalProductDto(long id, long siteId, long productGroupId, String externalProductId, long policyId, SyncStatus status) {
        this.id = id;
        this.siteId = siteId;
        this.productGroupId = productGroupId;
        this.externalProductId = externalProductId;
        this.policyId = policyId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public long getSiteId() {
        return siteId;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public String getExternalProductId() {
        return externalProductId;
    }

    public long getPolicyId() {
        return policyId;
    }

    public SyncStatus getStatus() {
        return status;
    }
}
