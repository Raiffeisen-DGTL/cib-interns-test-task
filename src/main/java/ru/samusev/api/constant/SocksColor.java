package ru.samusev.api.constant;

import java.util.Arrays;

public enum SocksColor {
    RED,
    GREEN,
    BLUE,
    WHITE,
    YELLOW,
    BLACK;

    public static SocksColor getByName(String name) {
        return Arrays.stream(SocksColor.values())
                .filter(color -> color.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Bad color: %s", name)));
    }
}
