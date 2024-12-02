package com.ryuqq.setof.support.external.core.buyma;

import com.ryuqq.setof.support.external.core.ExternalMallRequestStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BuyMaProductResponseHandler {

    public <T> ExternalMallRequestStatus handleResponse(ResponseEntity<BuyMaResponse<T>> response) {
        HttpStatusCode statusCode = response.getStatusCode();
        BuyMaResponse<T> body = response.getBody();

        if (statusCode.is2xxSuccessful()) {
            return ExternalMallRequestStatus.fromResponse(
                        statusCode.value(),
                        null,
                        true,
                        null,
                        0
            );

        } else if ((statusCode.is4xxClientError() || statusCode.is5xxServerError()) && body != null) {
            if (body.getT() instanceof BuyMaResponse.Failure failure) {
                return ExternalMallRequestStatus.fromResponse(
                        statusCode.value(),
                        null,
                        false,
                        failure.getErrors().toString(),
                        0
                );
            }
        }

        return ExternalMallRequestStatus.fromResponse(
                statusCode.value(),
                null,
                false,
                "Unhandled response type or missing body",
                0
        );
    }

    public ExternalMallRequestStatus handleError(int statusCode, String errorMessage) {
        return ExternalMallRequestStatus.failed(
                statusCode,
                errorMessage
        );
    }

}
