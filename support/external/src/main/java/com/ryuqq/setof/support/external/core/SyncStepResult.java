package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.enums.core.SyncStep;

public class SyncStepResult {

    private final SyncStep step;
    private final boolean success;
    private final SyncStatus status;
    private final Integer errorCode;
    private final String errorMessage;
    private final ExternalMallProductContext externalMallProductContext;
    private final ExternalMallProductContext.Builder externalMallProductContextBuilder;
    private final String requestBody;

    private SyncStepResult(SyncStep step, boolean success, SyncStatus status, Integer errorCode, String errorMessage, ExternalMallProductContext.Builder externalMallProductContextBuilder, String requestBody) {
        this.step = step;
        this.success = success;
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.externalMallProductContextBuilder = externalMallProductContextBuilder;
        this.externalMallProductContext = externalMallProductContextBuilder.build();
        this.requestBody = requestBody;
    }

    public static SyncStepResult success(SyncStep step, ExternalMallProductContext.Builder externalMallProductContextBuilder, String requestBody) {
        return new SyncStepResult(step, true, null, null, null, externalMallProductContextBuilder, requestBody);
    }

    public static SyncStepResult success(SyncStep step, SyncStatus status, ExternalMallProductContext.Builder externalMallProductContextBuilder, String requestBody) {
        return new SyncStepResult(step, true, status, null, null, externalMallProductContextBuilder, requestBody);
    }

    public static SyncStepResult failure(SyncStep step, Integer errorCode, String errorMessage, ExternalMallProductContext.Builder externalMallProductContextBuilder, String requestBody) {
        return new SyncStepResult(step, false, SyncStatus.FAILED, errorCode, errorMessage, externalMallProductContextBuilder, requestBody);
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

    public SyncStatus getStatus() {
        return status;
    }

    public ExternalMallProductContext.Builder getExternalMallProductContextBuilder() {
        return externalMallProductContextBuilder;
    }

    public ExternalMallProductContext getExternalMallProductContext() {
        return externalMallProductContext;
    }
}
