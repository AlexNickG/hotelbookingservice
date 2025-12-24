package com.example.hotelbookingservice.kafka.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@Document("statistics")
public class KafkaMessage {

    //    String id;
    private String type; // "Register user", "Booking room"

    private String userId;

    private LocalDate checkIn;

    private LocalDate checkOut;
//    private Map<String, Object> payload;
}
