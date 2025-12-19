package com.example.hotelbookingservice.kafka.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@Document("messages")
public class KafkaMessage {

    @Id
    private String id;
    private String type; // "Register user", "Booking room"
    private Map<String, Object> payload;
}
