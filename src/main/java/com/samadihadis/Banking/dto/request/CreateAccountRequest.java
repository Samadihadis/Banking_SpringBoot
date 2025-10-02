package com.samadihadis.Banking.dto.request;

import com.samadihadis.Banking.enums.AccountStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountRequest {
    @NotNull(message = "شماره حساب نمی تواند خالی باشد")
    @Size(min = 10, message = "طول شماره حساب حداقل باید 10 رقم باشد")
    private String accountNumber;
    private String shebaNumber;
    private Double balance;
    private Long customerId;
    private Long bankId;
    private AccountStatus status;
}
