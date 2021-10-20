package ru.samusev.api.constant;

public enum Operation {
    EQUALS("equals"),
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan");

    private final String title;

    Operation(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
