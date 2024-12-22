package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.Origin;

public class ExternalProductPolicyDto {

    private final long siteId;
    private final long policyId;
    private final Origin countryCode;
    private final boolean translated;

    @QueryProjection
    public ExternalProductPolicyDto(long siteId, long policyId, Origin countryCode, boolean translated) {
        this.siteId = siteId;
        this.policyId = policyId;
        this.countryCode = countryCode;
        this.translated = translated;
    }

    public long getSiteId() {
        return siteId;
    }

    public long getPolicyId() {
        return policyId;
    }

    public Origin getCountryCode() {
        return countryCode;
    }

    public boolean isTranslated() {
        return translated;
    }
}
