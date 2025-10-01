package com.samadihadis.Banking.dto.request;

import com.samadihadis.Banking.services.account.types.AccountStatus;

public class StatusUpdateRequest {
    private AccountStatus accountStatus;

    public AccountStatus getNewStatus() { return accountStatus; }
    public void setNewStatus(AccountStatus newStatus) { this.accountStatus = accountStatus; }
}
