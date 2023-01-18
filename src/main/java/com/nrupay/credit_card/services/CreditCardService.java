package com.nrupay.credit_card.services;

import com.nrupay.credit_card.domain.CreditCard;
import com.nrupay.credit_card.model.CreditCardRequestResponse;
import com.nrupay.credit_card.repositories.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCardRequestResponse saveCreditCardDetails(CreditCardRequestResponse request) {
        CreditCard creditCard = new CreditCard();
        creditCard = creditCardRepository.save(creditCard.toCreditCard(request));
        return request.toCreditCardRequestResponse(creditCard);
    }

    public List<CreditCardRequestResponse> getAll() {
        List<CreditCardRequestResponse> allCreditCards = new ArrayList<>();
        creditCardRepository.findAll().parallelStream().forEach(creditCard -> {
            CreditCardRequestResponse creditCardRequestResponse = new CreditCardRequestResponse();
            allCreditCards.add(creditCardRequestResponse.toCreditCardRequestResponse(creditCard));
        });
        return allCreditCards;
    }
}
