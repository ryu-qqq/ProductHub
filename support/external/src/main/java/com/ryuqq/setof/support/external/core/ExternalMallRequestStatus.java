package com.ryuqq.setof.support.external.core;

import java.time.LocalDateTime;

public record ExternalMallRequestStatus(int statusCode, String responseMessage, boolean success,
                                        String errorDetails, int retryCount, LocalDateTime processedAt) {

    public static ExternalMallRequestStatus fromResponse(int statusCode, String responseMessage, boolean success, String errorDetails, int retryCount) {
        return new ExternalMallRequestStatus(statusCode, responseMessage, success, errorDetails, retryCount, LocalDateTime.now());
    }

    public static ExternalMallRequestStatus failed(int statusCode, String errorDetails) {
        return new ExternalMallRequestStatus(statusCode, "Failure", false, errorDetails, 0, LocalDateTime.now());
    }


}