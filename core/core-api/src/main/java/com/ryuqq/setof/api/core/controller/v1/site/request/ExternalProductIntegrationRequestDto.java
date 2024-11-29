package com.ryuqq.setof.api.core.controller.v1.site.request;

import com.ryuqq.setof.enums.core.SyncStatus;

public record ExternalProductIntegrationRequestDto(
        long siteId,
        long sellerId,
        SyncStatus status,
        int pageSize
) {
}
