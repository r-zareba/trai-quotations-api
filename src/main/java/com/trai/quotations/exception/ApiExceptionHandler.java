package com.trai.quotations.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {InternalServerError.class})
    public ResponseEntity<Object> handleApiRequestException(InternalServerError ex) {
        ApiException exception = new ApiException(
                InternalServerError.ERROR,
                InternalServerError.STATUS_CODE,
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(exception, InternalServerError.STATUS);
    }
}
