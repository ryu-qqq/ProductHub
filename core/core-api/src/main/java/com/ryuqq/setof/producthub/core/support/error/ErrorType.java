package com.ryuqq.setof.producthub.core.support.error;

import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;


public enum ErrorType {

    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "An unexpected error has occurred.",
                  LogLevel.ERROR),

    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, ErrorCode.E404, "Resource not found.",
                    LogLevel.WARN),

    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, ErrorCode.E400, "Bad Request",
            LogLevel.WARN),

    ;

    private final HttpStatus status;

    private final ErrorCode code;

    private final String message;

    private final LogLevel logLevel;

    ErrorType(HttpStatus status, ErrorCode code, String message, LogLevel logLevel) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.logLevel = logLevel;
    }


    public static ErrorType of(int status) {
        switch (status) {
            case 400 -> {
                return BAD_REQUEST_ERROR;
            }
            case 404-> {
                return NOT_FOUND_ERROR;
            }
            default -> {
                return DEFAULT_ERROR;
            }
        }
    }



    public HttpStatus getStatus() {
        return status;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

}
