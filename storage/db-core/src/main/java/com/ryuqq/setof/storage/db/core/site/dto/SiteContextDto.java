package com.ryuqq.setof.storage.db.core.site.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.core.Origin;
import com.ryuqq.setof.core.SiteType;

public class SiteContextDto {

    private long siteId;
    private String siteName;
    private String baseUrl;
    private Origin countryCode;
    private SiteType siteType;

    @QueryProjection
    public SiteContextDto(long siteId, String siteName, String baseUrl, Origin countryCode, SiteType siteType) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.baseUrl = baseUrl;
        this.countryCode = countryCode;
        this.siteType = siteType;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Origin getCountryCode() {
        return countryCode;
    }

    public SiteType getSiteType() {
        return siteType;
    }
}
