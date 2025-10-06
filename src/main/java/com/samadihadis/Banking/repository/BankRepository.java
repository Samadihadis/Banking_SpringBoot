package com.samadihadis.Banking.repository;

import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank , Long> {
}
