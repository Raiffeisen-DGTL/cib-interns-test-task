package com.example.socks.management.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Operation {
    EQUAL,
    MORETHEN,
    LESSTHEN;

        @JsonCreator
        public static Operation fromString(String title) {
            return title == null
                    ? null
                    : Operation.valueOf(title.toUpperCase());
        }

    public static boolean isLegalString(String title) {
        boolean result = false;
        for (Operation operation : Operation.values()) {
            if (operation.toString().equals(title.toUpperCase())) {
                result = true;
            }
        }
        return result;
    }

    }
