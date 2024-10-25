package com.ryuqq.setof.producthub.core.exception;

import com.ryuqq.setof.producthub.core.support.ApiResponse;
import com.ryuqq.setof.producthub.core.support.error.ErrorMessage;
import com.ryuqq.setof.producthub.core.support.error.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private static final String ERROR_LOG_MSG_FORMAT = "Class : {}, ERROR Message : {}";

    private final Logger log = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handleCoreException(ApplicationException e) {
        switch (e.getErrorType().getLogLevel()) {
            case ERROR -> log.error("CoreException : {}", e.getMessage(), e);
            case WARN -> log.warn("CoreException : {}", e.getMessage(), e);
            default -> log.info("CoreException : {}", e.getMessage(), e);
        }

        return ResponseEntity
                .status(e.getErrorType().getStatus())
                .body(ApiResponse.error(e.getErrorMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {

        String fieldErrors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> String.format("%s", fieldError.getDefaultMessage()))
                .collect(Collectors.joining(" "));

        String globalErrors = e.getBindingResult().getGlobalErrors()
                .stream()
                .map(objectError -> String.format("%s", objectError.getDefaultMessage()))
                .collect(Collectors.joining(" "));

        String errorMsg = (fieldErrors + " " + globalErrors).trim();

        String exceptionClassName = e.getClass().getSimpleName();
        log.error(ERROR_LOG_MSG_FORMAT, exceptionClassName, errorMsg);

        ErrorMessage errorMessage = new ErrorMessage(ErrorType.of(HttpStatus.BAD_REQUEST.value()));

        return ResponseEntity
                .status(errorMessage.getStatus())
                .body(ApiResponse.error(errorMessage));
    }



}
