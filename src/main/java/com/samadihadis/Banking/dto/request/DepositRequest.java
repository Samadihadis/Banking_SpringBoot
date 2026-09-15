package com.samadihadis.Banking.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class DepositRequest {
    private Long accountId;
    private BigDecimal amount;
    private String description;
}
