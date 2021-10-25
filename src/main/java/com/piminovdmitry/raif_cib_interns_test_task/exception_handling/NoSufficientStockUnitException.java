package com.piminovdmitry.raif_cib_interns_test_task.exception_handling;

public class NoSufficientStockUnitException extends RuntimeException {
    public NoSufficientStockUnitException(String message) {
        super(message);
    }
}
