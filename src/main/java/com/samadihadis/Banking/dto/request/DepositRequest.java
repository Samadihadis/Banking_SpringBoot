package com.samadihadis.Banking.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepositRequest {
    private Long accountId;
    private Double amount;
    private String description;
}
