package com.samadihadis.Banking.dto.response;


import com.samadihadis.Banking.enums.AccountStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateAccountResponse {
    private Long accountId;
    private String accountNumber;
    private String shebaNumber;
    private AccountStatus status;
    private BigDecimal balance;
    private Long customerId;
    private Long bankId;
}
