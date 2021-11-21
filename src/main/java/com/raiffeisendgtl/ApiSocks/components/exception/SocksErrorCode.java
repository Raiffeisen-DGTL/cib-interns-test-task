package com.raiffeisendgtl.ApiSocks.components.exception;

import org.springframework.http.HttpStatus;

public enum SocksErrorCode {

    INCORRECT_PARAMS(HttpStatus.BAD_REQUEST),
    SERVER_CRASH(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus statusError;

    SocksErrorCode(HttpStatus statusError) {
        this.statusError = statusError;
    }

    public HttpStatus getStatusError() {
        return statusError;
    }

}
