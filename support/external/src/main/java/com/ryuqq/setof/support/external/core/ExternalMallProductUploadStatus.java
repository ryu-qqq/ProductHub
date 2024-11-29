package com.ryuqq.setof.support.external.core;

import java.time.LocalDateTime;

public record ExternalMallProductUploadStatus(int statusCode, String responseMessage, boolean success,
                                              String errorDetails, int retryCount, LocalDateTime processedAt) {

    public static ExternalMallProductUploadStatus fromResponse(int statusCode, String responseMessage, boolean success, String errorDetails, int retryCount) {
        return new ExternalMallProductUploadStatus(statusCode, responseMessage, success, errorDetails, retryCount, LocalDateTime.now());
    }

    public static ExternalMallProductUploadStatus failed(String errorDetails) {
        return new ExternalMallProductUploadStatus(500, "Failure", false, errorDetails, 0, LocalDateTime.now());
    }


}