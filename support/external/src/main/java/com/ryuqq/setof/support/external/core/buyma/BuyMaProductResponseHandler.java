package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.ExternalMallProductUploadStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BuyMaProductResponseHandler {


    public ExternalMallProductUploadStatus handleSuccess(ResponseEntity<BuyMaResponse.Success> response) {
        return ExternalMallProductUploadStatus.fromResponse(
                response.getStatusCode().value(),
                Objects.requireNonNull(response.getBody()).getRequestUid(),
                true,
                null,
                0
        );
    }

    public ExternalMallProductUploadStatus handleError(int statusCode, String errorMessage) {
        return ExternalMallProductUploadStatus.fromResponse(
                statusCode,
                errorMessage,
                false,
                errorMessage,
                0
        );
    }

}
