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
    private Long customerId;

    private String customerFullName;

    @Column(unique = true)
    private String nationalId;

    @Column(unique = true)
    private String customerCode;

}
