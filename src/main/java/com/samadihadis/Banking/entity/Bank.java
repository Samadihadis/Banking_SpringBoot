package com.samadihadis.Banking.entity;

import jakarta.persistence.*;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bankId;

    @Column(unique = true)
    String bankName;

    @Column(unique = true)
    String branch;

    public Bank() {}

    public Bank(String bankName, String branch) {
        this.bankName = bankName;
        this.branch = branch;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
