package ru.vsu.service.validation;

import ru.vsu.service.validation.validator.IsAvailableQuantityForOutcome;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = {IsAvailableQuantityForOutcome.class})
public @interface IsAvailableQuantity {

  String message() default "there are not so many socks in the storage";

  Class<? extends Payload>[] payload() default {};

  Class<?>[] groups() default {};
}
