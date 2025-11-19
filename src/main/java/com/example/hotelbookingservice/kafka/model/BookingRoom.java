package com.example.hotelbookingservice.kafka.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "bookings")
public class BookingRoom {

    private Long userId;

    private LocalDate checkIn;

    private LocalDate checkOut;
}
