package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.SiteType;

import java.util.List;

public interface SiteQueryService {
    boolean siteExist(long siteId);
    boolean siteExist(String name, String baseUrl);
    List<Site> findSiteResponse(SiteFilter siteFilter);
    long findSiteCount(SiteFilter siteFilter);
    SiteContext findSiteContext(long siteId);
    SiteProfile findSiteProfile(SiteType siteType, long siteId, long mappingId);
}
