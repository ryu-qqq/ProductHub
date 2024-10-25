package com.ryuqq.setof.producthub.core.support;

import com.ryuqq.setof.producthub.core.support.error.ErrorMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse extends Response{
    private final String timestamp;
    private final ErrorMessage error;


    public ErrorResponse(ErrorMessage errorMessage) {
        super(errorMessage.getStatus().value(), errorMessage.getMessage());
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.error = errorMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ErrorMessage getError() {
        return error;
    }
}
