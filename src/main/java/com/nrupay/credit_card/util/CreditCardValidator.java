package com.nrupay.credit_card.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


class CreditCardValidator implements ConstraintValidator<CreditCardNumber, Long> {

    @Override
    public boolean isValid(Long creditCardNumber, ConstraintValidatorContext constraintValidatorContext) {
        return ValidateCardNumber.validityCheck(creditCardNumber);
    }


}
