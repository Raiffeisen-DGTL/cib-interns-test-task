package com.oleg.socks.validator;

public class CottonPartValidator  {
    public static boolean isValid(String input) {
        int intInput;
        try {
            intInput = Integer.parseInt(input);
        }
        catch (NumberFormatException ex) {
            return false;
        }
        return intInput >= 0 && intInput <= 100;
    }
}
