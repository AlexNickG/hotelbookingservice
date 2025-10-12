package com.example.hotelbookingservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RoomResponse {

    private Long id;

    private Long hotelId;

    private String name;

    private String description;

    private Integer number;

    private BigDecimal cost;

    private Integer capacity;

    private LocalDate occupanceStart;

    private LocalDate occupancyEnd;
}
