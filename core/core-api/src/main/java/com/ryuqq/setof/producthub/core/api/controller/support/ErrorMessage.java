package com.ryuqq.setof.producthub.core.api.controller.support;


import com.ryuqq.setof.domain.core.exception.ErrorType;
import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private final String code;
    private final String message;
    private final HttpStatus status;


    public ErrorMessage(ErrorType errorType) {
        this.code = errorType.getCode().name();
        this.message = errorType.getMessage();
        this.status = HttpStatus.valueOf(errorType.getStatus());
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }


    public ErrorResponse toErrorResponse(){
        return new ErrorResponse(this);
    }
}