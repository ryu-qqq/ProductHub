package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;

public record SyncResult(
        SiteName siteName,
        Integer statusCode,
        boolean success,
        String message,
        long productGroupId,
        String externalProductId,
        String requestBody
) {}
