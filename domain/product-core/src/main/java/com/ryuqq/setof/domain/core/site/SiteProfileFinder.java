package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.SiteType;

public interface SiteProfileFinder {

    SiteType getSiteType();
    SiteProfile fetchSiteProfile(long siteId, SiteType siteType);
}
