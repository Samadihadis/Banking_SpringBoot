package com.samadihadis.Banking.entity;


import com.samadihadis.Banking.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    private Double transactionAmount;

    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    private String transactionDescription;

    public Transaction() {
    }

    public Transaction(Account sourceAccount, Account destinationAccount,
                       Double transactionAmount, LocalDate transactionDate, TransactionStatus transactionStatus, String transactionDescription) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.transactionDescription = transactionDescription;
    }

}
