// src/main/java/com/example/registropersonasgym/events/ClienteEventProducer.java
package com.example.registropersonasgym.events;

import com.gym.events.dto.ClienteEventDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClienteEventProducer {
    private final KafkaTemplate<String, ClienteEventDTO> kafkaTemplate;
    @Value("${cliente.events.topic:cliente-events}")
    private String topic;

    public ClienteEventProducer(KafkaTemplate<String, ClienteEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClienteEvent(ClienteEventDTO event) {
        kafkaTemplate.send(topic, event.getClienteId().toString(), event);
    }
}
