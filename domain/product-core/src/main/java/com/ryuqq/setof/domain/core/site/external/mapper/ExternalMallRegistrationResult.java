package com.ryuqq.setof.domain.core.site.external.mapper;

public class ExternalMallRegistrationResult {

    private ExternalMallProductPayload externalMallProductPayload;
    private ExternalMallProductUploadStatus externalMallProductUploadStatus;

    public ExternalMallRegistrationResult(ExternalMallProductPayload externalMallProductPayload, ExternalMallProductUploadStatus externalMallProductUploadStatus) {
        this.externalMallProductPayload = externalMallProductPayload;
        this.externalMallProductUploadStatus = externalMallProductUploadStatus;
    }

    public ExternalMallProductPayload getExternalMallProductPayload() {
        return externalMallProductPayload;
    }

    public ExternalMallProductUploadStatus getExternalMallProductUploadStatus() {
        return externalMallProductUploadStatus;
    }
}
