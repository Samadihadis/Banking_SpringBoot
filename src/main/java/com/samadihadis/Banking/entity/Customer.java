package com.samadihadis.Banking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long customerId;

    String customerFullName;

    @Column(unique = true)
    String nationalId;

    @Column(unique = true)
    String customerCode;

}
