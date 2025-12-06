package com.samadihadis.Banking.dto.event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestedEvent {

    private Long transactionId;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private Double amount;
    private String description;
}
