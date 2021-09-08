package com.example.product_service.core.errorhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ProductServiceErrorHandler {

    @SuppressWarnings("unused")
    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException exception, WebRequest request) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleOtherExceptions  (Exception exception, WebRequest request) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }
}
