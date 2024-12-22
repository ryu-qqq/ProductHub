package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.SiteType;
import com.ryuqq.setof.db.core.site.dto.SiteFilterStorageDto;

public record SiteFilter(
        SiteType siteType,
        Long cursorId,
        int pageSize
) {

    public SiteFilterStorageDto toSiteFilterStorageDto(){
        return new SiteFilterStorageDto(siteType, cursorId, pageSize);
    }
}
