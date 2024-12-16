package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.enums.core.SyncStatus;

public record SyncResult(
        SiteName siteName,
        Integer statusCode,
        boolean success,
        SyncStatus status,
        String message,
        long productGroupId,
        String externalProductGroupId,
        String requestBody
) {}
