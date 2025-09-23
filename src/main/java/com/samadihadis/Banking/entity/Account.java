package com.samadihadis.Banking.entity;

import com.samadihadis.Banking.enums.Status;
import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String accountNumber;

    @Column(unique = true)
    String shebaNumber;

    Status status;
    Double balance;

    public Account() {}

    public Account(String accountNumber, String shebaNumber, Status status, Double balance) {
        this.accountNumber = accountNumber;
        this.shebaNumber = shebaNumber;
        this.status = status;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getShebaNumber() {
        return shebaNumber;
    }

    public void setShebaNumber(String shebaNumber) {
        this.shebaNumber = shebaNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
