package com.samadihadis.Banking.repository;

import com.samadihadis.Banking.entity.Account;
import com.samadihadis.Banking.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(Long customerId);

    List<Account> findByBankId(Long bankId);

    List<Account> findByAccountStatus(AccountStatus accountStatus);

}
