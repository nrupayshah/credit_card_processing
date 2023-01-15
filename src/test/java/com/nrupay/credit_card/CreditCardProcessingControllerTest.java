package com.nrupay.credit_card;

import com.nrupay.credit_card.controller.CreditCardController;
import com.nrupay.credit_card.model.CreditCard;
import com.nrupay.credit_card.services.CreditCardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreditCardController.class)
public class CreditCardProcessingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CreditCardService creditCardService;

    @Captor
    private ArgumentCaptor<CreditCard> creditCardArgumentCaptor;

    @Test
    @DisplayName("Should add validated card details")
    public void creditCardDetailAddTest() throws Exception {

        Mockito.when(creditCardService.saveCreditCardDetails(creditCardArgumentCaptor.capture())).thenReturn(CreditCard.builder().build());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "number":"4440967484181607",
                                    "limit": "1000.00",
                                    "name" : "test card"
                                }
                                """)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
        assertThat(creditCardArgumentCaptor.getValue().getName(), is("test card"));
        assertThat(creditCardArgumentCaptor.getValue().getLimit(), is(1000.00));
        assertThat(creditCardArgumentCaptor.getValue().getNumber(), is(4440967484181607L));
    }

    @Test
    @DisplayName("Should throw an error when credit card number validation fails")
    public void creditCardDetailAddThrowErrorTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "number":"444096748",
                                    "limit": "1000.00",
                                    "name" : "test card"
                                }
                                """)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Should retrieve all the saved credit card details")
    public void retrieveCreditCardDetailsTest() throws Exception {
        List<CreditCard> creditCards = List.of(CreditCard.builder().id(1).name("First Credit Card").balance(0L).number(4440967484181607L).limit(1000L).build(),
                CreditCard.builder().id(2).name("Second Credit Card").balance(0L).number(4440967484181607L).limit(1000L).build());
        Mockito.when(creditCardService.getAll()).thenReturn(creditCards);
        mockMvc.perform(MockMvcRequestBuilders.get("/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("First Credit Card")))
                .andExpect(jsonPath("$[0].balance", is(0)))
                .andExpect(jsonPath("$[0].limit", is(1000.0)))
                .andExpect(jsonPath("$[0].number", is(4440967484181607L)));
    }
}
