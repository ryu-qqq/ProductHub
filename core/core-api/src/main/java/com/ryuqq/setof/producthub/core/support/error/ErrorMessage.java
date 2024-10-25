package com.ryuqq.setof.producthub.core.support.error;


import com.ryuqq.setof.producthub.core.support.ErrorResponse;
import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private final String code;
    private final String message;
    private final HttpStatus status;


    public ErrorMessage(ErrorType errorType) {
        this.code = errorType.getCode().name();
        this.message = errorType.getMessage();
        this.status = errorType.getStatus();
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
