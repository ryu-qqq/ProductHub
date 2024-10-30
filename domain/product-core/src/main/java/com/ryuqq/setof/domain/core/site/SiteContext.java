package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.Origin;
import com.ryuqq.setof.core.SiteType;

public class SiteContext {

    private long siteId;
    private String siteName;
    private String baseUrl;
    private Origin countryCode;
    private SiteType siteType;
    private SiteProfile siteProfile;

    public void setSiteProfile(SiteProfile siteProfile) {
        this.siteProfile = siteProfile;
    }

    public SiteContext(long siteId, String siteName, String baseUrl, Origin countryCode, SiteType siteType, SiteProfile siteProfile) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.baseUrl = baseUrl;
        this.countryCode = countryCode;
        this.siteType = siteType;
        this.siteProfile = siteProfile;
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

    public SiteProfile getSiteProfile() {
        return siteProfile;
    }
}
