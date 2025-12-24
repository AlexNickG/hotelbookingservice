package com.example.hotelbookingservice.kafka.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document("statistics")
public class KafkaMessage {

    private String type; // "Register user", "Booking room"

    private String userId;

    private LocalDate checkIn;

    private LocalDate checkOut;
}
