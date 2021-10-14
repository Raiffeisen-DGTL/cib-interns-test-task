package raiffeisen.utils;

import java.util.Arrays;


/**
 * @author voroningg
 */
public enum Operator {
    MoreThan("moreThan"),
    LessThan("lessThan"),
    Equal("equal");

    private final String value;

    Operator(String value) {
        this.value = value;
    }

    public static Operator parse(String value) {
        return Arrays.stream(values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .get();
    }

    public static boolean isParsable(String value) {
        return Arrays.stream(values())
                .anyMatch(v -> v.value.equals(value));
    }
}
