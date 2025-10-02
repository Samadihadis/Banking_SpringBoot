package com.samadihadis.Banking.entity;

import com.samadihadis.Banking.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = "accountNumber"))
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true, length = 50)
    private String accountNumber;

    @Column(unique = true)
    private String shebaNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status = AccountStatus.OPEN;

    private Double balance = 0d;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;


    @PrePersist
    void prePersist() {
        if (status == null) status = AccountStatus.OPEN;
        if (balance == null) balance = 0d;
    }

    public Account() {}

}
