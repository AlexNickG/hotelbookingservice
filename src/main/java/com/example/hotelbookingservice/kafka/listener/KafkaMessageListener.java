package com.example.hotelbookingservice.kafka.listener;

import com.example.hotelbookingservice.kafka.model.KafkaMessage;
import com.example.hotelbookingservice.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageListener {

    private final StatisticService service;

    @KafkaListener(topics = "${app.kafka.bookingRoomTopic}",
            groupId = "${app.kafka.statisticGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    private void listenBookingRoom(@Payload KafkaMessage message,
                        @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        message.setType("Booking room");
        log.info("Received booking message: {}", message);
        service.saveMessage(message);

    }

    @KafkaListener(topics = "${app.kafka.registerUserTopic}",
            groupId = "${app.kafka.statisticGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    private void listenRegisterUser(@Payload KafkaMessage message,
                        @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        message.setType("Register user");
        log.info("Received user register message: {}", message);
        service.saveMessage(message);

    }
}
