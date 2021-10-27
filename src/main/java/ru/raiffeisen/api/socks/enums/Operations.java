package ru.raiffeisen.api.socks.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public enum Operations {
    MORE_THAN("moreThan"),
    LESS_THAN("lessThan"),
    EQUAL("equal"),
    UNKNOWN(Strings.EMPTY);

    @Getter
    private String operation;

    private static final Map<String, Operations> BY_CODES = Arrays.stream(Operations.values())
            .collect(Collectors.toMap(Operations::getOperation, Function.identity()));

    public static Operations byCode(String code) {
        return Optional.ofNullable(BY_CODES.get(code)).orElse(Operations.UNKNOWN);
    }
}
