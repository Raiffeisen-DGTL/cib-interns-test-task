package ru.hlem.storesocksraif.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Operation {
    MORE_THAN("moreThan"),
    EQUAL("equal"),
    LESS_THAN("lessThan");

    private String text;

    Operation(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Optional<Operation> fromText(String text) {
        return Arrays.stream(values())
                .filter(operation -> operation.text.equals(text))
                .findFirst();
    }
}
