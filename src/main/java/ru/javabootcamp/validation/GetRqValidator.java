package ru.javabootcamp.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class GetRqValidator {

    public void validate(String color, String operation, Integer cottonPart) {

        String errorMsg = "";
        List<String> operationValues = Arrays.asList("moreThan", "lessThan", "equal");

        if (color.length() < 2 || color.length() > 30)
            errorMsg += "color must have the length between 2 and 30;";

        if (!color.matches("[a-zа-я]+"))
            errorMsg += "color must contains only latin or russian lowercase letters;";

        if (!operationValues.contains(operation))
            errorMsg += "operation must be moreThan or lessThan or equal;";

        if (cottonPart < 0 || cottonPart > 100)
            errorMsg += "cottonPart must be between 0 and 100";

        if (errorMsg != "")
            throw new ConstraintViolationException(errorMsg, new HashSet<ConstraintViolation<?>>());
    }
}
