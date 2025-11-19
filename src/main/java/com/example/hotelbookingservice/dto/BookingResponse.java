package com.example.hotelbookingservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingResponse {

    private Long id;

    private Long userId;

    private Long roomId;

    private LocalDate checkIn;

    private LocalDate checkOut;
}
