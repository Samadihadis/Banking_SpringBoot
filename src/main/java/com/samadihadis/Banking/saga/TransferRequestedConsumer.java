package com.samadihadis.Banking.saga;

import com.samadihadis.Banking.dto.event.TransferRequestedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferRequestedConsumer {

    @KafkaListener(
            topics = "transfer_requested",
            groupId = "banking-service",
            containerFactory = "kafkaListenerFactory"
    )
    public void consume(TransferRequestedEvent event) {
        log.info("Received Kafka Event: {}", event);
        System.out.println("Event received => " + event);
    }
}
