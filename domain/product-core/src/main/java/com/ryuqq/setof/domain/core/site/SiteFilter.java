package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.SiteType;
import com.ryuqq.setof.storage.db.core.site.dto.SiteFilterStorageDto;

public record SiteFilter(
        SiteType siteType,
        Long cursorId,
        int pageSize
) {

    public SiteFilterStorageDto toSiteFilterStorageDto(){
        return new SiteFilterStorageDto(siteType, cursorId, pageSize);
    }
}
