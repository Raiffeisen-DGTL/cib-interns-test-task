package com.ziborov.raifproject.validator;

import com.ziborov.raifproject.dto.ComparisonOperation;
import com.ziborov.raifproject.exception.RequestValidationException;
import com.ziborov.raifproject.model.Socks;

public class SocksValidator {

    public static void validateSocksValues(String color, String operation, Integer cottonPart) {
        if (Socks.SocksColor.fromString(color) == null) {
            throw new RequestValidationException("Socks color is invalid, please use another value");
        }
        if (ComparisonOperation.fromString(operation) == null) {
            throw new RequestValidationException("Operation is invalid, please use another value");
        }
        if (cottonPart == null || cottonPart < 0 || cottonPart > 100) {
            throw new RequestValidationException("Cotton part is invalid, please use another value. Cotton part value should be from 0 to 100");
        }
    }

}