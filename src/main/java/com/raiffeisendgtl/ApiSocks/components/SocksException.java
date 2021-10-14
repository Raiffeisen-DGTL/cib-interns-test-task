package com.raiffeisendgtl.ApiSocks.components;

import org.springframework.stereotype.Component;

@Component
public class SocksException extends RuntimeException {

    private SocksErrorCode error;

    public SocksException() {

    }

    public SocksException(SocksErrorCode error) {
        this.error = error;
    }

    public SocksErrorCode getError() {
        return error;
    }

}
