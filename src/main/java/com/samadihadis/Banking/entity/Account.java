package com.samadihadis.Banking.entity;

import com.samadihadis.Banking.enums.AccountStatus;
import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(unique = true)
    private String accountNumber;

    @Column(unique = true)
    private String shebaNumber;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Account() {}

    public Account(String accountNumber, String shebaNumber, AccountStatus status, Double balance,
                   Customer customer, Bank bank) {
        this.accountNumber = accountNumber;
        this.shebaNumber = shebaNumber;
        this.status = status;
        this.balance = balance;
        this.customer = customer;
        this.bank = bank;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
