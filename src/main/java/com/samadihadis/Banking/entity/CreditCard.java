package com.samadihadis.Banking.entity;

import com.samadihadis.Banking.enums.AccountStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditCardId;

    @Column(unique = true)
    private String cardNumber;

    private LocalDate expirationDate;
    private String cvv2;
    private AccountStatus status;


    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public CreditCard() {}

    public CreditCard(AccountStatus status, String cardNumber, LocalDate expirationDate, String cvv2, Account account) {
        this.status = status;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv2 = cvv2;
        this.account = account;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
