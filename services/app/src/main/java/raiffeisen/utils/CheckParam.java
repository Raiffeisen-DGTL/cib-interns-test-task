package raiffeisen.utils;

import java.util.Objects;

/**
 * @author voroningg
 */
public class CheckParam {
    public static boolean isNotInRange(int param, int min, int max) {
        return param < min || param > max;
    }

    public static boolean isLessThan(int param, int min) {
        return param < min;
    }

    public static boolean isNullOrEmpty(String color) {
        return color == null || Objects.equals(color, "");
    }

    public static boolean isExistingOperator(String operator) {
        return Operator.isParsable(operator);
    }
}
