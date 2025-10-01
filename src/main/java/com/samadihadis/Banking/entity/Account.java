package com.samadihadis.Banking.entity;

import com.samadihadis.Banking.services.account.types.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

}
