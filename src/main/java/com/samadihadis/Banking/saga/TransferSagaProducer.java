package com.samadihadis.Banking.saga;

import com.samadihadis.Banking.dto.event.TransferRequestedEvent;
import com.samadihadis.Banking.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferSagaProducer {

    private final KafkaTemplate<String, TransferRequestedEvent> kafkaTemplate;

    private static final String TOPIC = "transfer_requested";

    public void sendTransferRequested(Transaction transaction) {

        TransferRequestedEvent event = new TransferRequestedEvent(
                transaction.getTransactionId(),
                transaction.getSourceAccount().getAccountId(),
                transaction.getDestinationAccount().getAccountId(),
                transaction.getTransactionAmount(),
                transaction.getTransactionDescription()
        );

        kafkaTemplate.send(TOPIC, event);
        System.out.println("Event sent to Kafka => " + event);
    }
}
