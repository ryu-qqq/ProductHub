package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.SiteType;

import java.util.List;

public interface SiteProfileFinder {

    SiteType getSiteType();
    List<? extends SiteProfile> fetchSiteProfile(long siteId);
    SiteProfile fetchSiteProfile(long siteId, long mappingId);
}
