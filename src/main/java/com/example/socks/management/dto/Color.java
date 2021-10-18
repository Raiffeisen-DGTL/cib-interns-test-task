package com.example.socks.management.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Color {
    BLACK,
    WHITE,
    YELLOW,
    GREEN,
    RED,
    BLUE;


    @JsonCreator
    public static Color fromString(String title) {
        return title == null
                ? null
                : Color.valueOf(title.toUpperCase());
    }

    public static boolean isLegalString(String title) {
        boolean result = false;
        for (Color color : Color.values()) {
            if (color.toString().equals(title.toUpperCase())) {
                result = true;
            }
        }
        return result;
    }

}
