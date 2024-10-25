package com.ryuqq.setof.producthub.core.exception;

import com.ryuqq.setof.producthub.core.support.error.ErrorMessage;
import com.ryuqq.setof.producthub.core.support.error.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class ApplicationException extends RuntimeException{

    private final ErrorType errorType;
    private final ErrorMessage errorMessage;
    private BindingResult errors;


    protected ApplicationException(HttpStatus httpStatus, String message) {
        super(message);
        this.errorType = ErrorType.of(httpStatus.value());
        this.errorMessage = new ErrorMessage(ErrorType.of(httpStatus.value()));
    }

    protected ApplicationException(HttpStatus httpStatus, String message, BindingResult errors) {
        super(message);
        this.errorType = ErrorType.of(httpStatus.value());
        this.errorMessage = new ErrorMessage(ErrorType.of(httpStatus.value()));
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return errorMessage.getMessage();
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public BindingResult getErrors() {
        return errors;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
