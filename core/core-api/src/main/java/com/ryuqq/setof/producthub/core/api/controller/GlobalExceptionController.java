package com.ryuqq.setof.producthub.core.api.controller;

import com.ryuqq.setof.domain.core.exception.ApplicationException;
import com.ryuqq.setof.domain.core.exception.ErrorType;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import com.ryuqq.setof.producthub.core.api.controller.support.ErrorMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;
import java.util.stream.Collectors;

@RestControllerAdvice
@RestController
public class GlobalExceptionController {

    private static final String ERROR_LOG_MSG_FORMAT = "CoreException : {}";

    private final Logger log = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handleCoreException(ApplicationException e) {
        switch (e.getErrorType().getLogLevel()) {
            case ERROR -> log.error(ERROR_LOG_MSG_FORMAT, e.getMessage(), e);
            case WARN -> log.warn(ERROR_LOG_MSG_FORMAT, e.getMessage(), e);
            default -> log.info(ERROR_LOG_MSG_FORMAT, e.getMessage(), e);
        }

        return ResponseEntity
                .status(e.getErrorType().getStatus())
                .body(ApiResponse.error(new ErrorMessage(e.getErrorType())));
    }


    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<?> handleValidationExceptions(InvalidParameterException e) {
        String errorMsg = e.getMessage();
        log.warn(ERROR_LOG_MSG_FORMAT, errorMsg, e);

        ErrorType errorType = ErrorType.of(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(ApiResponse.error(new ErrorMessage(errorType)));
    }


}
