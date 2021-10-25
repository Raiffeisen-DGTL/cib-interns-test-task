package ru.raiffeisen.socks.service.impl;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Operation {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUALS("equals"),
    UNSUPPORTED(Strings.EMPTY);

    private final String value;

    Operation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    private static final Map<String, Operation> VALUES = Arrays.stream(Operation.values())
            .collect(Collectors.toMap(Operation::getValue, Function.identity()));

    public static Operation byValue(String value) {
        return Optional.ofNullable(VALUES.get(value)).orElse(Operation.UNSUPPORTED);
    }
}