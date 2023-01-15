package com.nrupay.credit_card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreditCardProcessingControllerTest {

    @Test
    @DisplayName("Should add validated card details")
    public void creditCardDetailAddTest() {

    }

    @Test
    @DisplayName("Should throw an error when credit card number validation fails")
    public void creditCardDetailAddThrowErrorTest() {

    }

    @Test
    @DisplayName("Should retrieve all the saved credit card details")
    public void retrieveCreditCardDetailsTest() {

    }

    @Test
    @DisplayName("Should return 404 in case no credit card details found")
    public void retrieveCreditCardDetailsNotFoundTest() {

    }

}
