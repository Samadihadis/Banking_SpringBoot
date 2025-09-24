package com.samadihadis.Banking.repository;

import com.samadihadis.Banking.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
