package com.vadim01er.taskraiffeisenbank.entity;

import lombok.Getter;

@Getter
public enum Operation {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal");

    private final String name;

    Operation(String name) {
        this.name = name;
    }

    public static Operation fromString(String name) {
        for (Operation b : Operation.values()) {
            if (b.name.equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }
}
