package com.samadihadis.Banking.entity;

import com.samadihadis.Banking.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
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

    public CreditCard() {
    }

}
