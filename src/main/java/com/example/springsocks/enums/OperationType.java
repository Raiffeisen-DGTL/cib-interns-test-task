package com.example.springsocks.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Операторы сравнения значения.
 *
 * @author Alexander_Tupchin
 */
@RequiredArgsConstructor
@Getter
public enum OperationType {

    /**
     * Больше чем.
     */
    MORE_THAN("moreThan"),
    /**
     * Меньше чем.
     */
    LESS_THAN("lessThan"),
    /**
     * Эквивалентно.
     */
    EQUAL("equal");

    private final String message;
}
