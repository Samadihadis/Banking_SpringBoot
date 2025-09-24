package com.samadihadis.Banking.entity;


import com.samadihadis.Banking.enums.TransactionStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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

    public Transaction() {}

    public Transaction(Account sourceAccount, Account destinationAccount,
                       Double transactionAmount, LocalDate transactionDate, TransactionStatus transactionStatus, String transactionDescription) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.transactionDescription = transactionDescription;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }


    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
}
