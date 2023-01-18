package com.nrupay.credit_card.controller;

import com.nrupay.credit_card.domain.CreditCard;
import com.nrupay.credit_card.model.CreditCardRequestResponse;
import com.nrupay.credit_card.services.CreditCardService;
import com.nrupay.credit_card.util.ValidateCardNumber;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditCardRequestResponse> saveCreditCardDetails(@Valid @RequestBody CreditCardRequestResponse creditCardRequest) {
        CreditCardRequestResponse creditCard = creditCardService.saveCreditCardDetails(creditCardRequest);
        return new ResponseEntity<>(creditCard, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CreditCardRequestResponse>> getAll() {
        return ResponseEntity.ok(creditCardService.getAll());
    }

    @GetMapping(value = "/validate/{number}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity validateCreditCardNumber(@PathVariable long number) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("isValid", ValidateCardNumber.validityCheck(number));
        return ResponseEntity.ok().body(response);
    }
}
