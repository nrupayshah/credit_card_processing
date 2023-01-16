package com.nrupay.credit_card.model;

import com.nrupay.credit_card.util.CreditCardNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class CreditCard {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @NotEmpty
    private String name;

    @Column
    @CreditCardNumber
    private long number;

    @Column
    private double limit;

    @Column
    private long balance;
}
