package com.raiffeisen.raiffeisen_test_task.model;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    private final Date timestamp;
    private final String message;
}
