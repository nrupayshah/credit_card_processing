package com.nrupay.credit_card.model;


import com.nrupay.credit_card.domain.CreditCard;
import com.nrupay.credit_card.util.CreditCardNumber;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRequestResponse {


        @NotEmpty
        private String name;
        @CreditCardNumber
        private long number;
        private double limit;
        private long balance;

        public CreditCardRequestResponse toCreditCardRequestResponse(CreditCard creditCard) {
                this.name = creditCard.getName();
                this.number = creditCard.getNumber();
                this.balance = creditCard.getBalance();
                this.limit = creditCard.getLimit();
                return this;
        }
    }
