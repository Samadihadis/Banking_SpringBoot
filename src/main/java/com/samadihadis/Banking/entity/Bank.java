package com.samadihadis.Banking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bankId;

    @Column(unique = true)
    String bankName;

    @Column(unique = true)
    String branch;

    public Bank() {
    }

}
