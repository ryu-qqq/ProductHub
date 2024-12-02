package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;

public record SyncFailureInfo(
        Integer errorCode,
        String errorMessage,
        SyncStep step,
        long productGroupId,
        String requestBody
) {
}
