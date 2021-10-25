package com.piminovdmitry.raif_cib_interns_test_task.service;

public enum CompOperation {
    MORE_THAN,
    LESS_THAN,
    EQUAL;

    public String getMathSimbol() {
        if (this == MORE_THAN) {
            return ">";
        } else if (this == LESS_THAN) {
            return "<";
        } else {
            return "=";
        }
    }

    public static CompOperation getEnum(String OperationName) {
        if (OperationName.compareToIgnoreCase("moreThan") == 0) {
            return CompOperation.MORE_THAN;
        } else if (OperationName.compareToIgnoreCase("lessThan") == 0) {
            return CompOperation.LESS_THAN;
        } else if (OperationName.compareToIgnoreCase("equal") == 0) {
            return CompOperation.EQUAL;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
