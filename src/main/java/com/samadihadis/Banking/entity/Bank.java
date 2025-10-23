package com.samadihadis.Banking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
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
