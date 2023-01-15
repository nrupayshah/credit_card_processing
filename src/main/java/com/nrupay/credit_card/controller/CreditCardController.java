package com.nrupay.credit_card.controller;

import com.nrupay.credit_card.model.CreditCard;
import com.nrupay.credit_card.services.CreditCardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveCreditCardDetails(@Valid @RequestBody CreditCard creditCardRequest) {
        creditCardService.saveCreditCardDetails(creditCardRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CreditCard>> getAll() {
        return ResponseEntity.ok(creditCardService.getAll());
    }
}
