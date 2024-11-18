package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.domain.core.site.SiteFilter;
import com.ryuqq.setof.enums.core.SiteType;

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
