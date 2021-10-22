package com.example.socks.utils;

public enum Operation {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal");

    private String operation;

    Operation(String operation)
    {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
