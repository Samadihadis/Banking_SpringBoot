package com.samadihadis.Banking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long customerId;

    String customerFullName;

    @Column(unique = true)
    String nationalId;

    @Column(unique = true)
    String customerCode;

    public Customer() {
    }

    public Customer(String fullName, String nationalId, String customerCode) {
        this.customerFullName = fullName;
        this.nationalId = nationalId;
        this.customerCode = customerCode;
    }

}
