package ru.raiffeisen.socks.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=SockOperationConstraintValidator.class)
public @interface SockOperationConstraint {
    String message() default "Неподдерживаемая операция";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
