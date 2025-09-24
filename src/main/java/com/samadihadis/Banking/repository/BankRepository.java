package com.samadihadis.Banking.repository;

import com.samadihadis.Banking.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank , Long> {
}
