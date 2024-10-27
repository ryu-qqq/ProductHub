package com.ryuqq.setof.domain.core.exception;



import org.springframework.validation.BindingResult;

public class ApplicationException extends RuntimeException{

    private final ErrorType errorType;
    private BindingResult errors;


    protected ApplicationException(int value, String message) {
        super(message);
        this.errorType = ErrorType.of(value);
    }

    protected ApplicationException(int value, String message, BindingResult errors) {
        super(message);
        this.errorType = ErrorType.of(value);
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return errorType.getMessage() + super.getMessage();
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public BindingResult getErrors() {
        return errors;
    }
}
