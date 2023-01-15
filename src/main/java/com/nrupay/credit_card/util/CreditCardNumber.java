package com.nrupay.credit_card.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CreditCardValidator.class)
@Documented
public @interface CreditCardNumber {

    String message() default "Credit card number is not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
