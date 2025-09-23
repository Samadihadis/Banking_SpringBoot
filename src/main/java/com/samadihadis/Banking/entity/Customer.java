package com.samadihadis.Banking.entity;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
