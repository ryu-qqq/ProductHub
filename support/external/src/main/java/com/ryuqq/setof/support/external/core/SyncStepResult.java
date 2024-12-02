package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;

public class SyncStepResult {

    private final SyncStep step;
    private final boolean success;
    private final Integer errorCode;
    private final String errorMessage;
    private final Object resultData;

    private SyncStepResult(SyncStep step, boolean success, Integer errorCode, String errorMessage, Object resultData) {
        this.step = step;
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.resultData = resultData;
    }

    public static SyncStepResult success(SyncStep step, Object resultData) {
        return new SyncStepResult(step, true, null, null, resultData);
    }

    public static SyncStepResult failure(SyncStep step, Integer errorCode, String errorMessage, Object resultData) {
        return new SyncStepResult(step, false, errorCode, errorMessage, resultData);
    }

    public SyncStep getStep() {
        return step;
    }

    public boolean isSuccess() {
        return success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Object getResultData() {
        return resultData;
    }





}
