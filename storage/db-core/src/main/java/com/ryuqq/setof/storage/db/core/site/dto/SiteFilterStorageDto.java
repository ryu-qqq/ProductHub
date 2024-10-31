package com.ryuqq.setof.storage.db.core.site.dto;

import com.ryuqq.setof.core.SiteType;

public record SiteFilterStorageDto(
        SiteType siteType,
        Long cursorId,
        int pageSize
) {
}
