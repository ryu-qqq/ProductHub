package com.ryuqq.setof.support.external.core;

import java.util.List;

public record SyncResultSummary(
        List<SyncStepResult> successResponses,
        List<SyncStepResult> failureResponses
) {}
