package ru.strelchm.raiffeisenchallenge.dto;

import ru.strelchm.raiffeisenchallenge.exception.BadRequestException;

import java.util.Arrays;

public enum SockCompareOperation {
    GREATER_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal");

    SockCompareOperation(String name) {
        this.name = name;
    }

    private final String name;

    public static SockCompareOperation getByName(String name) {
        return Arrays.stream(values()).filter(v -> v.name.equals(name)).findFirst().orElseThrow(BadRequestException::new);
    }
}
