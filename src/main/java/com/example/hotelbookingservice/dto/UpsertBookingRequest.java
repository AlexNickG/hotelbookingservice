package com.example.hotelbookingservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpsertBookingRequest {

    private Long roomId;

    private LocalDate checkIn;

    private LocalDate checkOut;
}
