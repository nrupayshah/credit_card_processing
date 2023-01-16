package com.nrupay.credit_card.services;

import com.nrupay.credit_card.model.CreditCard;
import com.nrupay.credit_card.repositories.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard saveCreditCardDetails(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public List<CreditCard> getAll() {
        return creditCardRepository.findAll();
    }
}
