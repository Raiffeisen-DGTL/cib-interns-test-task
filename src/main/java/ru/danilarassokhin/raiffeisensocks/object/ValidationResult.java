package ru.danilarassokhin.raiffeisensocks.object;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationResult {

    private final Set<ConstraintViolation<Object>> violations;

    public ValidationResult(Set<ConstraintViolation<Object>> validationResult)
    {
        violations = validationResult;
    }

    /**
     * Returns first constrain violation object
     * @return First constraint violation or null if object is valid
     */
    public ConstraintViolation<Object> getFirstViolation()
    {
        return violations.stream().findFirst().orElse(null);
    }

    /**
     * Returns message from first constraint violation
     * @return First constrain violation message of empty string if objecy is valid
     */
    public String getFirstErrorMessage()
    {
        String message = "";
        ConstraintViolation<Object> constraintViolation = getFirstViolation();
        if(constraintViolation != null)
        {
            message = constraintViolation.getMessage();
        }
        return message;
    }

    /**
     * Checks if violated object is violated
     * @return true if object is valid
     */
    public boolean isValid()
    {
        return violations.size() == 0;
    }

}
