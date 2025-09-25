package com.samadihadis.Banking.dto;

import com.samadihadis.Banking.enums.AccountStatus;

public class AccountRequest {
    private String accountNumber;
    private String shebaNumber;
    private Double balance;
    private Long customerId;
    private Long bankId;
    private AccountStatus status;

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getShebaNumber() { return shebaNumber; }
    public void setShebaNumber(String shebaNumber) { this.shebaNumber = shebaNumber; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getBankId() { return bankId; }
    public void setBankId(Long bankId) { this.bankId = bankId; }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
