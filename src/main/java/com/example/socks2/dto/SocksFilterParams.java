package com.example.socks2.dto;

import com.example.socks2.exception.InvalidArgumentException;

public class SocksFilterParams {
    private final String color;
    private final OperationType operationType;
    private final Long cottonPart;

    public SocksFilterParams(String color, String operationType, Long cottonPart) {
        this.color = color;
        this.operationType = OperationType.getByAlias(operationType);
        this.cottonPart = cottonPart;
    }

    public String getColor() {
        return color;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Long getCottonPart() {
        return cottonPart;
    }

    public enum OperationType {
        MORE_THAN("moreThan"),
        LESS_THAN("lessThan"),
        EQUAL("equal");

        private final String name;

        OperationType(String name) {
            this.name = name;
        }

        public static OperationType getByAlias(String name) {
            for (var value : values()) {
                if (value.name.equals(name)) {
                    return value;
                }
            }

            throw new InvalidArgumentException();
        }
    }
}
