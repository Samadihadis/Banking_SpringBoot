package com.samadihadis.Banking.service;

import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.entity.CreditCard;
import com.samadihadis.Banking.enums.CardStatus;
import com.samadihadis.Banking.repository.AccountRepository;
import com.samadihadis.Banking.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;


    public CreditCard createCreditCard(
            CreditCard creditCard,
            Long accountId
    ) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        creditCard.setAccount(account);

        if (creditCard.getCardStatus() == null) {
            creditCard.setCardStatus(CardStatus.OPEN);
        }

        return creditCardRepository.save(creditCard);
    }


    public CreditCard getCreditCardById(Long id) {

        return creditCardRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Credit card not found"));
    }


    public CreditCard getCreditCardByNumber(String cardNumber) {

        return creditCardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() ->
                        new RuntimeException("Credit card not found"));
    }


    public CreditCard getCreditCardByAccountId(Long accountId) {

        return creditCardRepository.findByAccountId(accountId)
                .orElseThrow(() ->
                        new RuntimeException("Credit card not found"));
    }


    public List<CreditCard> getAllCreditCards() {

        return creditCardRepository.findAll();
    }


    @Transactional
    public CreditCard updateCardStatus(
            Long creditCardId,
            CardStatus cardStatus
    ) {

        CreditCard creditCard = getCreditCardById(creditCardId);

        creditCard.setCardStatus(cardStatus);

        return creditCardRepository.save(creditCard);
    }


    public void deleteCreditCard(Long id) {

        CreditCard creditCard = getCreditCardById(id);

        creditCardRepository.delete(creditCard);
    }
}
