package com.example.registropersonasgym.events;

import com.gym.events.dto.ClienteEventDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ClienteEventProducer {

    private static final Logger log = LoggerFactory.getLogger(ClienteEventProducer.class);
    private final KafkaTemplate<String, ClienteEventDTO> kafkaTemplate;

    @Value("${cliente.events.topic:cliente-events}")
    private String topic;

    public ClienteEventProducer(KafkaTemplate<String, ClienteEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClienteEvent(ClienteEventDTO event) {
        CompletableFuture<SendResult<String, ClienteEventDTO>> future = kafkaTemplate
                .send(topic, event.getClienteId().toString(), event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("❌ Error al enviar evento a Kafka: {}", ex.getMessage(), ex);
            } else {
                log.info("✅ Evento enviado correctamente a Kafka: {}", event);
            }
        });
    }
}
