package com.raiffeisen.raiffeisen_test_task.service;

import com.raiffeisen.raiffeisen_test_task.exception.RaiffeisenException.ValidationException;
import com.raiffeisen.raiffeisen_test_task.model.Socks;

public class SocksValidator {
    public static void validateSocks(Socks socks) {
        validateColor(socks.getColor());
        validateCottonPart(socks.getCottonPart());
        validateQuantity(socks.getQuantity());
    }

    public static void validateSocks(String color, byte cottonPart) {
        validateColor(color);
        validateCottonPart(cottonPart);
    }

    public static void validateQuantitiesBeforeSubtraction(long DBQuantity, long subtractionQuantity) {
        if (subtractionQuantity > DBQuantity)
            throw new ValidationException("You are trying to remove more socks than you have");
    }

    private static void validateColor(String color) {
        if (color.trim().length() == 0)
            throw new ValidationException("Empty string instead of color");
    }

    private static void validateCottonPart(byte cottonPart) {
        if (cottonPart < 0 || cottonPart > 100)
            throw new ValidationException("Incorrect percentage in cotton part");
    }

    private static void validateQuantity(long quantity) {
        if (quantity < 0)
            throw new ValidationException("Incorrect quantity");
    }
}
