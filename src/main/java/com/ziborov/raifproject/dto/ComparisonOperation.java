package com.ziborov.raifproject.dto;

public enum ComparisonOperation {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal");

    private final String fieldName;

    ComparisonOperation(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static ComparisonOperation fromString(String operation) {
        for (ComparisonOperation comparisonOperation : ComparisonOperation.values()) {
            if (comparisonOperation.getFieldName().equals(operation)) {
                return comparisonOperation;
            }
        }

        return null;
    }
}