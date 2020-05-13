package com.trai.quotations.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ApiException {

    private final String error;
    private final int status;
    private final String message;
    private final Date timestamp;

}
