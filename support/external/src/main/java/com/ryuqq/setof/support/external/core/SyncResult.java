package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.enums.core.SyncStatus;

import java.util.List;

public record SyncResult(
        SiteName siteName,
        Integer statusCode,
        boolean success,
        SyncStatus status,
        String message,
        long productGroupId,
        String externalProductGroupId,
        List<ExternalMallSyncedOption> externalMallSyncedOptions,
        String requestBody
) {}
