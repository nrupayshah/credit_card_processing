package com.nrupay.credit_card.services;

import com.nrupay.credit_card.domain.CreditCard;
import com.nrupay.credit_card.model.CreditCardRequestResponse;
import com.nrupay.credit_card.repositories.CreditCardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
@ExtendWith(MockitoExtension.class)
public class CreditCardServiceTest {

    @InjectMocks
    CreditCardService creditCardService;

    @Mock
    CreditCardRepository creditCardRepository;

    @Test
    @DisplayName("Should save the credit card details")
    public void saveCreditCardDetails() {
        CreditCard card = CreditCard.builder().limit(1000).name("my credit card").number(4440967484181607L).build();
        CreditCardRequestResponse cardRequest = CreditCardRequestResponse.builder().limit(1000).name("my credit card").number(4440967484181607L).build();


        Mockito.when(creditCardRepository.save(card)).thenReturn(CreditCard.builder().limit(1000).name("my credit card").number(4440967484181607L).id(1L).build());
        CreditCardRequestResponse savedCreditCard = creditCardService.saveCreditCardDetails(cardRequest);
        assertThat(savedCreditCard.getName(), is("my credit card"));
    }

    @Test
    public void retrieveAllRecords() {
        CreditCard card = CreditCard.builder().limit(1000).name("my credit card").number(4440967484181607L).build();
        CreditCardRequestResponse response = new CreditCardRequestResponse();
        response = response.toCreditCardRequestResponse(card);
        Mockito.when(creditCardRepository.findAll()).thenReturn(List.of(card));
        List<CreditCardRequestResponse> creditCards = creditCardService.getAll();
        assertThat(creditCards.size(), is(1));
        assertThat(creditCards.get(0), is(response));
    }
}
