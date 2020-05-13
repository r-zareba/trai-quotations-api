package com.trai.quotations.exception;

import org.springframework.http.HttpStatus;

public class InternalServerError extends RuntimeException {

    public static final String ERROR = "Internal Server Error";
    public static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    public static final int STATUS_CODE = STATUS.value();

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }
}
