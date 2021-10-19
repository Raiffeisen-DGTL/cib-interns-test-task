package com.example.socksservice.dto;

public enum CountOperation {
    MORE_THAN("morethan"), LESS_THAN("lessthan"), EQUAL("equal");
    private final String operation;

    CountOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public static CountOperation createFromString(String operation) {
        String lowerCase = operation.toLowerCase();
        if (lowerCase.equals(MORE_THAN.getOperation())) {
            return MORE_THAN;
        }

        if (lowerCase.equals(LESS_THAN.getOperation())) {
            return LESS_THAN;
        }

        if (lowerCase.equals(EQUAL.getOperation())) {
            return EQUAL;
        }

        throw new UnsupportedOperationException();
    }
}
