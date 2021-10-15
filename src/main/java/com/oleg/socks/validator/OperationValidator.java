package com.oleg.socks.validator;

public class OperationValidator {
    public static boolean isValid(String input) {
        return input.equals("moreThan")||input.equals("lessThan")||input.equals("equal");
    }
}
