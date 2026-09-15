package com.samadihadis.Banking.repository;

import com.samadihadis.Banking.entity.Transaction;
import com.samadihadis.Banking.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.sourceAccount.accountId = :accountId " +
            "OR t.destinationAccount.accountId = :accountId")
    List<Transaction> findAllByAccountId(@Param("accountId") Long accountId);
}
