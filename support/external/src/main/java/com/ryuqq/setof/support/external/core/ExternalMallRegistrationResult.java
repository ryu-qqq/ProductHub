package com.ryuqq.setof.support.external.core;


import com.ryuqq.setof.enums.core.SyncStatus;

import java.math.BigDecimal;
import java.util.List;

public class ExternalMallRegistrationResult{

    private final ExternalMallRegistrationRequest registrationRequest;
    private final ExternalMallProductUploadStatus uploadStatus;
    private final ExternalMallUpdateResult externalMallUpdateResult;


    public ExternalMallRegistrationResult(ExternalMallRegistrationRequest registrationRequest, ExternalMallProductUploadStatus uploadStatus, long productGroupId, String externalProductId, long siteId, String productName, BigDecimal regularPrice, BigDecimal currentPrice, List<ExternalMallImageResult> externalMallImageResults) {
        this.registrationRequest = registrationRequest;
        this.uploadStatus = uploadStatus;
        this.externalMallUpdateResult = new ExternalMallUpdateResult(
                productGroupId,
                siteId,
                externalProductId, productName,
                regularPrice, currentPrice,
                success() ? SyncStatus.APPROVED : SyncStatus.FAILED,
                false, true,
                externalMallImageResults
                );
    }

    public static ExternalMallRegistrationResult failedResult(long productGroupId, String errorMessage, long siteId) {
        ExternalMallProductUploadStatus failedStatus = ExternalMallProductUploadStatus.failed(errorMessage);
        return new ExternalMallRegistrationResult(
                null,
                failedStatus,
                productGroupId,
                null,
                siteId,
                null,
                null,
                null,
                List.of()
        );
    }


    public ExternalMallRegistrationRequest getRegistrationRequest() {
        return registrationRequest;
    }

    public ExternalMallProductUploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public ExternalMallUpdateResult getExternalMallUpdateResult() {
        return externalMallUpdateResult;
    }

    private boolean success(){
        return uploadStatus.success();
    }
}
