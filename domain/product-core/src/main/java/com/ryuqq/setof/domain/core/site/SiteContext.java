package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.enums.core.SiteType;

import java.util.List;

public class SiteContext {

    private long siteId;
    private String siteName;
    private String baseUrl;
    private Origin countryCode;
    private SiteType siteType;
    private List<SiteProfile> siteProfiles;


    public SiteContext(long siteId, String siteName, String baseUrl, Origin countryCode, SiteType siteType, List<SiteProfile> siteProfiles) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.baseUrl = baseUrl;
        this.countryCode = countryCode;
        this.siteType = siteType;
        this.siteProfiles = siteProfiles;
    }

    public long getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public SiteName getSiteNameEnum() {
        return SiteName.of(siteName);
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

    public List<SiteProfile> getSiteProfiles() {
        return siteProfiles;
    }
}
