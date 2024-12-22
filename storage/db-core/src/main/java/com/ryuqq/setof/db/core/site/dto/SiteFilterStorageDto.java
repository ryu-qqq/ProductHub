package com.ryuqq.setof.storage.db.core.site.dto;

import com.ryuqq.setof.enums.core.SiteType;

public record SiteFilterStorageDto(
        SiteType siteType,
        Long cursorId,
        int pageSize
) {
}
