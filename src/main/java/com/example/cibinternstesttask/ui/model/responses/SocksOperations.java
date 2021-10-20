package com.example.cibinternstesttask.ui.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum SocksOperations {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal");

    private String operation;

    private static final Map<String, SocksOperations> BY_OPERATION_MAP = new LinkedHashMap<>();
    static {
        for (SocksOperations socksOperations : SocksOperations.values()) {
            BY_OPERATION_MAP.put(socksOperations.operation, socksOperations);
        }
    }

    public static SocksOperations forOperation(String operation) {
        return BY_OPERATION_MAP.get(operation);
    }
}
