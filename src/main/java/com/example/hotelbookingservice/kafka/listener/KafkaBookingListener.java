package com.example.hotelbookingservice.kafka.listener;

import com.example.hotelbookingservice.kafka.model.BookingRoom;
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
@RequiredArgsConstructor
@Slf4j
public class KafkaBookingListener {

    private final KafkaService kafkaService;

    @KafkaListener(topics = "${app.kafka.bookingRoomTopic}",
            groupId = "${app.kafka.bookingRoomGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    private void listen(@Payload BookingRoom message,
                        @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("Received message: {}", message);
        kafkaService.addBooking(message);

    }
}
