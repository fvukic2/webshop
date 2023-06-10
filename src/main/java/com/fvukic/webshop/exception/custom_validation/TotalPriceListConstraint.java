package com.fvukic.webshop.exception.custom_validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TotalPriceListValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TotalPriceListConstraint {
    String message() default "Total price of articles must be greater than 50.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
