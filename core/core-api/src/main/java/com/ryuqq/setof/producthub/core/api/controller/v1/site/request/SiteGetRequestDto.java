package com.ryuqq.setof.producthub.core.api.controller.v1.site.request;

import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.domain.core.site.SiteFilter;

public record SiteGetRequestDto(
        SiteType siteType,
        Long cursorId,
        Integer pageSize
) {
    public SiteFilter toSiteFilter(){
        int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
        return new SiteFilter(siteType, cursorId, defaultSize);
    }
}
