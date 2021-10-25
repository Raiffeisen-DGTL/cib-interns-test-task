package ru.raiffeisen.soksapp.model;

import java.util.HashMap;
import java.util.Map;

public enum Operation {
    LESS_THAN,
    MORE_THAN,
    EQUALS;

    private static Map<String, Operation> namesMap = new HashMap<>(3);

    static {
        namesMap.put("lessThan", LESS_THAN);
        namesMap.put("moreThan", MORE_THAN);
        namesMap.put("equals", EQUALS);
    }


    public static Operation forValue(String value) {
        return namesMap.get(value);
    }
}
