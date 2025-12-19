package com.example.hotelbookingservice.kafka.listener;

import com.example.hotelbookingservice.kafka.model.RegisterUser;
import com.example.hotelbookingservice.kafka.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaRegisterListener {

    private final KafkaService kafkaService;

    @KafkaListener(topics = "${app.kafka.registerUserTopic}",
            groupId = "${app.kafka.registerUserGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    private void listen(@Payload RegisterUser message,
                        @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("Received message: {}", message);
        kafkaService.addRegister(message);

    }
}
