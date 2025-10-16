package com.samadihadis.Banking.dto.response;

import com.samadihadis.Banking.entity.Bank;
import com.samadihadis.Banking.entity.Customer;
import com.samadihadis.Banking.enums.AccountStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountResponse {
    private Long accountId;
    private String accountNumber;
    private String shebaNumber;
    private AccountStatus status;
    private Double balance;
    private Long customerId;
    private Long bankId;
}
