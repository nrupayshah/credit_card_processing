package com.nrupay.credit_card.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


/*
*
* */
class CreditCardValidator implements ConstraintValidator<CreditCardNumber, Long> {

    @Override
    public boolean isValid(Long creditCardNumber, ConstraintValidatorContext constraintValidatorContext) {
        return validityCheck(creditCardNumber);
    }

    // Return true if the card number is valid
    private boolean validityCheck(long creditCardNumber) {
        return (theSize(creditCardNumber) >= 13 && theSize(creditCardNumber) <= 16) && (prefixMatch(creditCardNumber, 4)
                || prefixMatch(creditCardNumber, 5) || prefixMatch(creditCardNumber, 37) || prefixMatch(creditCardNumber, 6))
                && ((sumDoubleEven(creditCardNumber) + sumOdd(creditCardNumber)) % 10 == 0);
    }
    // Get the result from Step 2
    private int sumDoubleEven(long creditCardNumber) {
        int sum = 0;
        String num = creditCardNumber + "";
        for (int i = theSize(creditCardNumber) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
        return sum;
    }
    // Return this creditCardNumber if it is a single digit, otherwise,
    // return the sum of the two digits
    private int getDigit(int creditCardNumber) {
        if (creditCardNumber < 9)
            return creditCardNumber;
        return creditCardNumber / 10 + creditCardNumber % 10;
    }
    // Return sum of odd-place digits in creditCardNumber
    private int sumOdd(long creditCardNumber) {
        int sum = 0;
        String num = creditCardNumber + "";
        for (int i = theSize(creditCardNumber) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }
    // Return true if the digit d is a prefix for creditCardNumber
    private boolean prefixMatch(long creditCardNumber, int d) {
        return getPrefix(creditCardNumber, theSize(d)) == d;
    }
    // Return the number of digits in d
    private int theSize(long d) {
        String num = d + "";
        return num.length();
    }
    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    private long getPrefix(long creditCardNumber, int k) {
        if (theSize(creditCardNumber) > k) {
            String num = creditCardNumber + "";
            return Long.parseLong(num.substring(0, k));
        }
        return creditCardNumber;
    }
}
