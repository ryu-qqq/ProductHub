package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;

public class SitePolicyDto {
    private final long siteId;
    private final long policyId;

    @QueryProjection
    public SitePolicyDto(long siteId, long policyId) {
        this.siteId = siteId;
        this.policyId = policyId;
    }

    public long getSiteId() {
        return siteId;
    }

    public long getPolicyId() {
        return policyId;
    }
}
