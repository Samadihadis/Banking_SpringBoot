package com.samadihadis.Banking.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class BalanceUpdateRequest {
    private BigDecimal newBalance;
}
