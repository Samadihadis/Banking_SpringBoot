package com.samadihadis.Banking.dto;

import com.samadihadis.Banking.enums.AccountStatus;

public class StatusUpdateRequest {
    private AccountStatus accountStatus;

    public AccountStatus getNewStatus() { return accountStatus; }
    public void setNewStatus(AccountStatus newStatus) { this.accountStatus = accountStatus; }
}
