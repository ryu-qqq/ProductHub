package com.ryuqq.setof.api.core.controller.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse extends Response {
    private final String timestamp;
    private final ErrorMessage error;


    public ErrorResponse(ErrorMessage errorMessage) {
        super(errorMessage.getStatus().value(), errorMessage.getMessage());
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.error = errorMessage;
    }

}
