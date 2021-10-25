package com.piminovdmitry.raif_cib_interns_test_task.exception_handling;

public class IncorrectRequestException extends RuntimeException{
    public IncorrectRequestException(String message) {
        super(message);
    }
}
