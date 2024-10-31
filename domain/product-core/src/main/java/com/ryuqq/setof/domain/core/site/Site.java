package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.Origin;
import com.ryuqq.setof.core.SiteType;

import java.util.Objects;

public class Site {
    private long siteId;
    private String siteName;
    private String baseUrl;
    private Origin countryCode;
    private SiteType siteType;

    public Site(long siteId, String siteName, String baseUrl, Origin countryCode, SiteType siteType) {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Site site = (Site) object;
        return siteId == site.siteId && Objects.equals(siteName, site.siteName) && Objects.equals(baseUrl, site.baseUrl) && countryCode == site.countryCode && siteType == site.siteType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(siteId, siteName, baseUrl, countryCode, siteType);
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", countryCode=" + countryCode +
                ", siteType=" + siteType +
                '}';
    }
}
