package com.samadihadis.Banking.dto.request;

import com.samadihadis.Banking.enums.AccountStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotNull(message = "شماره حساب نمی تواند خالی باشد")
    @Min(value = 10, message = "طول شماره حساب حداقل باید 10 رقم باشد")
    private String accountNumber;
    private String shebaNumber;
    private Double balance;
    private Long customerId;
    private Long bankId;
    private AccountStatus status;
}
