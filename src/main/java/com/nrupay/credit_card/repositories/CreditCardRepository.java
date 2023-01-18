package com.nrupay.credit_card.repositories;

import com.nrupay.credit_card.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
