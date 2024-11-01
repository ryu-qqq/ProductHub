package com.ryuqq.setof.domain.core.site;

import java.util.List;

public interface SiteQueryService {
    boolean siteExist(long siteId);
    boolean siteExist(String name, String baseUrl);
    List<Site> findSiteResponse(SiteFilter siteFilter);
    long findSiteCount(SiteFilter siteFilter);
    SiteContext findSiteContext(long siteId);
}
