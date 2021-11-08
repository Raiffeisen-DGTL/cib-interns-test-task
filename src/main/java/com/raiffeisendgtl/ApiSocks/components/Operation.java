package com.raiffeisendgtl.ApiSocks.components;

import com.raiffeisendgtl.ApiSocks.components.exception.SocksErrorCode;
import com.raiffeisendgtl.ApiSocks.components.exception.SocksException;

public enum Operation {

    moreThan,
    lessThan,
    equal;

    public static Operation convertFromString(String operationString) {
        try {
            return Operation.valueOf(operationString);
        } catch (Throwable e) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }
    }

}
