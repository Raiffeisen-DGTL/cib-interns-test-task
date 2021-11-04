package com.raiffeisendgtl.ApiSocks.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationTests {

    @Test
    public void convertFromStringIsCorrect() {
        String operation = "moreThan";
        Operation result = Operation.moreThan;

        Operation resultConversion = Operation.convertFromString(operation);

        assertEquals(result, resultConversion);
    }

    @Test
    public void convertFromStringIsError() {
        String operation = "lessTHAN";
        SocksErrorCode expectedResult = SocksErrorCode.INCORRECT_PARAMS;

        SocksException exception = assertThrows(SocksException.class, () -> {
            Operation.convertFromString(operation);
        });

        assertEquals(expectedResult, exception.getError());
    }

}
