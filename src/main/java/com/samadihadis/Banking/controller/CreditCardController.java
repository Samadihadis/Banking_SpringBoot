package com.samadihadis.Banking.controller;

import com.samadihadis.Banking.entity.CreditCard;
import com.samadihadis.Banking.enums.CardStatus;
import com.samadihadis.Banking.service.CreditCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-cards")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;


    @PostMapping
    public ResponseEntity<CreditCard> createCreditCard(
            @Valid @RequestBody CreditCard creditCard,
            @RequestParam Long accountId) {

        CreditCard createdCreditCard =
                creditCardService.createCreditCard(creditCard, accountId);

        return ResponseEntity.ok(createdCreditCard);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCreditCardById(
            @PathVariable Long id) {

        CreditCard creditCard =
                creditCardService.getCreditCardById(id);

        return ResponseEntity.ok(creditCard);
    }


    @GetMapping("/number/{cardNumber}")
    public ResponseEntity<CreditCard> getCreditCardByNumber(
            @PathVariable String cardNumber) {

        CreditCard creditCard =
                creditCardService.getCreditCardByNumber(cardNumber);

        return ResponseEntity.ok(creditCard);
    }


    @GetMapping("/account/{accountId}")
    public ResponseEntity<CreditCard> getCreditCardByAccountId(
            @PathVariable Long accountId) {

        CreditCard creditCard =
                creditCardService.getCreditCardByAccountId(accountId);

        return ResponseEntity.ok(creditCard);
    }


    @GetMapping
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {

        List<CreditCard> creditCards =
                creditCardService.getAllCreditCards();

        return ResponseEntity.ok(creditCards);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<CreditCard> updateCardStatus(
            @PathVariable Long id,
            @RequestParam CardStatus status) {

        CreditCard updatedCreditCard =
                creditCardService.updateCardStatus(id, status);

        return ResponseEntity.ok(updatedCreditCard);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCard(
            @PathVariable Long id) {

        creditCardService.deleteCreditCard(id);

        return ResponseEntity.noContent().build();
    }
}

