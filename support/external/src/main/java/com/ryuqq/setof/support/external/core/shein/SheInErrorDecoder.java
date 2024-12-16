package com.ryuqq.setof.support.external.core.shein;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class SheInErrorDecoder implements ErrorDecoder {



    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.UNAUTHORIZED.value() || response.status() == HttpStatus.FORBIDDEN.value()) {
            System.out.println("methodKey = " + methodKey);
            System.out.println("response = " + response);
        }

        return new Default().decode(methodKey, response);
    }
}
