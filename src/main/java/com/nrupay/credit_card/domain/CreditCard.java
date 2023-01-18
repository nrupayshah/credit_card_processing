package com.nrupay.credit_card.domain;

import com.nrupay.credit_card.model.CreditCardRequestResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private long number;

    @Column(name = "card_limit")
    private double limit;

    @Column
    private long balance;

    public CreditCard toCreditCard(CreditCardRequestResponse request) {
        this.name = request.getName();
        this.limit = request.getLimit();
        this.number = request.getNumber();
        return this;
    }
}
