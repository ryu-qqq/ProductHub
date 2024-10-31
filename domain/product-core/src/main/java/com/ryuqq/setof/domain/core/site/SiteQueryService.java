package com.ryuqq.setof.domain.core.site;

import java.util.List;

public interface SiteQueryService {

    List<Site> findSiteResponse(SiteFilter siteFilter);
    long findSiteCount(SiteFilter siteFilter);
    SiteContext findSiteContext(long siteId);

}
