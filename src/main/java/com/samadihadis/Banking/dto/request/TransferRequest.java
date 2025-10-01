package com.samadihadis.Banking.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferRequest {

    private Long sourceAccountId;
    private Long destinationAccountId;
    private String description;
    private Double amount;

}
