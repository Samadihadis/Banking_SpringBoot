package com.samadihadis.Banking.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TransferRequest {

    private Long sourceAccountId;
    private Long destinationAccountId;
    private String description;
    private BigDecimal amount;

}
