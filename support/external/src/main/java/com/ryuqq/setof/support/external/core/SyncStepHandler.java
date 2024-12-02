package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;

public interface SyncStepHandler {

    SyncStepResult execute(ExternalMallPreProductContext context);
    SyncStep getStep();
}
