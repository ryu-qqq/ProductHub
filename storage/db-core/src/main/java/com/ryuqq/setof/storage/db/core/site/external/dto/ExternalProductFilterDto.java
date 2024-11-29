package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.ryuqq.setof.enums.core.SyncStatus;

import java.util.List;

public record ExternalProductFilterDto(
        List<Long> siteIds,
        List<Long> sellerIds,
        SyncStatus status,
        Long cursorId,
        int pageSize,
        List<Long> productGroupIds
) {
}
