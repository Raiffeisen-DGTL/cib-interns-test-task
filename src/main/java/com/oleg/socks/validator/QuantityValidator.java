package com.oleg.socks.validator;

public class QuantityValidator  {

    public static boolean isValid(String input) {
            long longInput;
            try {
                longInput = Long.parseLong(input);
            }
            catch (NumberFormatException ex) {
                return false;
            }
            return longInput > 0;
        }
    }

