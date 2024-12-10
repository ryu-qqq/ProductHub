package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStep;

public class SyncStepResult {

    private final SyncStep step;
    private final boolean success;
    private final Integer errorCode;
    private final String errorMessage;
    private final ExternalMallProductContext externalMallProductContext;
    private final String requestBody;

    private SyncStepResult(SyncStep step, boolean success, Integer errorCode, String errorMessage, ExternalMallProductContext.Builder externalMallProductContextBuilder, String requestBody) {
        this.step = step;
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.externalMallProductContext = externalMallProductContextBuilder.build();
        this.requestBody = requestBody;
    }

    public static SyncStepResult success(SyncStep step, ExternalMallProductContext.Builder externalMallProductContextBuilder, String requestBody) {
        return new SyncStepResult(step, true, null, null, externalMallProductContextBuilder, requestBody);
    }

    public static SyncStepResult failure(SyncStep step, Integer errorCode, String errorMessage, ExternalMallProductContext.Builder externalMallProductContextBuilder, String requestBody) {
        return new SyncStepResult(step, false, errorCode, errorMessage, externalMallProductContextBuilder, requestBody);
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

    public String getRequestBody() {
        return requestBody;
    }

    public ExternalMallProductContext getExternalMallProductContext() {
        return externalMallProductContext;
    }
}
