package com.samadihadis.Banking.saga;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic transferTopic() {
        return TopicBuilder.name("transfer_requested")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
