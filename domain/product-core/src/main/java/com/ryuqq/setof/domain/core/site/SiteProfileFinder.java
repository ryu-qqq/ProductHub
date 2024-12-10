package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.SiteType;

import java.util.List;

public interface SiteProfileFinder {

    SiteType getSiteType();
    List<? extends SiteProfile> fetchBySiteId(long siteId);
    SiteProfile fetchBySiteId(long siteId, long mappingId);
}
