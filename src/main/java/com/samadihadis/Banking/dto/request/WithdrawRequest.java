package com.samadihadis.Banking.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawRequest {
    private Long accountId;
    private Double amount;
    private String description;

}
