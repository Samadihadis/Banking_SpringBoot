package com.samadihadis.Banking.entity;

import com.samadihadis.Banking.enums.Status;
import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditCardId;

    @Column(unique = true)
    private Long cardNumber;

    private LocalDate expirationDate;
    private String cvv2;
    private Status status;


    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public CreditCard() {}

    public CreditCard(Status status, Long cardNumber, LocalDate expirationDate, String cvv2, Account account) {
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

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
