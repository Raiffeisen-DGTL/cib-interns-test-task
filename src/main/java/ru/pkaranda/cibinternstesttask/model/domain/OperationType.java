package ru.pkaranda.cibinternstesttask.model.domain;

public enum OperationType {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal");

    private String value;

    OperationType(String value) {
    }

    public String getValue(){
        return this.value;
    }
}
