package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ExternalPolicyDto {

    private final long siteId;
    private final String siteName;
    private final long policyId;
    private final String name;
    private final String description;
    private final boolean activeYn;

    @QueryProjection
    public ExternalPolicyDto(long siteId, String siteName, long policyId, String name, String description, boolean activeYn) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.policyId = policyId;
        this.name = name;
        this.description = description;
        this.activeYn = activeYn;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public long getPolicyId() {
        return policyId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActiveYn() {
        return activeYn;
    }
}
