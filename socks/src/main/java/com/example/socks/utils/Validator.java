package com.example.socks.utils;

public class Validator {

    public static Operation getEnumByValue(String operation) {
        switch (operation) {
            case "equal":
                return Operation.EQUAL;
            case "moreThan":
                return Operation.MORE_THAN;
            case "lessThan":
                return Operation.LESS_THAN;
            default:
                return null;
        }
//        for (Operation operation1: Operation.values()) {
//            if (operation1.getOperation().equals(operation)) {
//                return true;
//            }
//        }
//        return false;
    }

    public static boolean validateCottonPart(int cottonPart) {
        return cottonPart >= 0 && cottonPart <= 100;
    }
}
