package ru.pkaranda.cibinternstesttask.model.domain;

public enum OperationType {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal"),
    EMPTY("");


    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static OperationType valueOfLabel(String label) {
        for (OperationType e : values()) {
            if (e.value.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
