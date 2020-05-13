package com.trai.quotations.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedError extends RuntimeException {

    public static final String ERROR = "Access Denied";
    public static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;
    public static final int STATUS_CODE = STATUS.value();

    public AccessDeniedError(String message) {
        super(message);
    }

    public AccessDeniedError(String message, Throwable cause) {
        super(message, cause);
    }
}
