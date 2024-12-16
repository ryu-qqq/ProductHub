package com.ryuqq.setof.support.external.core.shein;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class SheInResponseHandler {

    public <T> SheInResponse<T> handleResponse(ResponseEntity<SheInResponse<T>> response) {
        HttpStatusCode statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful() && response.getBody() != null && response.getBody().getCode().equals("0")) {
            return response.getBody();
        } else {
            if (response.getBody() != null) {
                throw new ResponseStatusException(statusCode, response.getBody().getMessage());
            }
            throw new ResponseStatusException(statusCode);
        }
    }

}
