package com.example.hotelbookingservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpsertRoomRequest {

    private Long hotelId;

    private String name;

    private String description;

    private Integer number;

    private BigDecimal cost;

    private Integer capacity;
}
