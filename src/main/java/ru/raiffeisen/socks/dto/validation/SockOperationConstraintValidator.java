package ru.raiffeisen.socks.dto.validation;

import ru.raiffeisen.socks.service.impl.Operation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SockOperationConstraintValidator implements ConstraintValidator<SockOperationConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Operation.byValue(value) != Operation.UNSUPPORTED;
    }
}
