package com.ryuqq.setof.support.external.core.oco;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OcoErrorHandler {

    private static final String OCO_AUTH_ERROR_MSG = "token 없음";
    private static final String OCO_DATA_NULL_MSG = "Response body is null";
    private static final String OCO_SERVER_ERROR_MSG = "Failed communication data";
    private static final String SUCCESS_MESSAGE = "Success";

    public <R> R handleResponse(ResponseEntity<OcoResponse<R>> response) {
        if (response == null) {
            throw new IllegalStateException(OCO_SERVER_ERROR_MSG);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            OcoResponse<R> body = response.getBody();
            if (body != null) {
                String message = body.responseStatus().returnMessage();
                if (OCO_AUTH_ERROR_MSG.equals(message)) {
                    throw new IllegalStateException("Unauthorized: Invalid token");
                }
                if (!SUCCESS_MESSAGE.equals(message)) {
                    throw new IllegalStateException(OCO_SERVER_ERROR_MSG);
                }
                return body.apiResult();
            } else {
                throw new IllegalStateException(OCO_DATA_NULL_MSG);
            }
        }

        throw new IllegalStateException(OCO_SERVER_ERROR_MSG);
    }
}