package com.samadihadis.Banking.repository;

import com.samadihadis.Banking.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    Optional<CreditCard> findByCardNumber(String cardNumber);
    Optional<CreditCard> findByAccountId(Long accountId);
}
