package ru.strelchm.raiffeisenchallenge.dto;

public enum SockCompareOperation {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal");

    SockCompareOperation(String name) {
        this.name = name;
    }

    private String name;
}
