package com.github.matveyakulov.raifweb.databind;

import com.github.matveyakulov.raifweb.enums.Operation;

public class OperationConverter {

    public static Operation convert(String operation) {

        operation = operation.toUpperCase();
        switch (operation) {
            case "MORETHAN": {
                return Operation.MORETHAN;
            }
            case "LESSTHAN": {
                return Operation.LESSTHAN;
            }
            case "EQUAL": {
                return Operation.EQUAL;
            }
            default: {
                return Operation.UNDEFIEND;
            }
        }

    }
}
