package ru.vsu.service.validation;


import ru.vsu.service.validation.validator.SocksPairExistForOutcome;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = {SocksPairExistForOutcome.class})
public @interface SocksPairExist {
  String message() default "socks pair doesn't exist";

  Class<? extends Payload>[] payload() default {};

  Class<?>[] groups() default {};
}
