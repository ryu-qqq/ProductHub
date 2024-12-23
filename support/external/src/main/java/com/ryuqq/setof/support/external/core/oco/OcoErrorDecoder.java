package com.ryuqq.setof.support.external.core.oco;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class OcoErrorDecoder implements ErrorDecoder {

    private final OcoAuthManager ocoAuthManager;

    public OcoErrorDecoder(OcoAuthManager ocoAuthManager) {
        this.ocoAuthManager = ocoAuthManager;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.UNAUTHORIZED.value() || response.status() == HttpStatus.FORBIDDEN.value()) {

            ocoAuthManager.refreshToken();
            throw new RetryableException(
                    response.status(),
                    "Token expired. Retrying with a new token.",
                    response.request().httpMethod(),
                    1L,
                    response.request());
        }

        return new Default().decode(methodKey, response);
    }
}
