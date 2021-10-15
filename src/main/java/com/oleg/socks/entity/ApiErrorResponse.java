package com.oleg.socks.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiErrorResponse {
    private String errorCode;
    private String errorMessage;
}
