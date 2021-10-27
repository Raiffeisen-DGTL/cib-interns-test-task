package ru.raiffeisen.socks.enums;

import org.apache.logging.log4j.util.Strings;

import java.util.stream.Stream;

public enum Operation {
    LESS_THAN("lessThan"),
    MORE_THAN("moreThan"),
    EQUAL("equal"),
    EMPTY(Strings.EMPTY);

    private final String code;

    Operation(String code) {
        this.code = code;
    }

    public static Operation decode(String code) {
        return Stream.of(Operation.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(EMPTY);
    }

}
