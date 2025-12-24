package com.example.hotelbookingservice.dto;

import com.example.hotelbookingservice.validation.RoomFilterValid;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RoomFilterValid
public class RoomFilter {

    private Long id;

    private String name;

    private BigDecimal minCost;

    private BigDecimal maxCost;

    private Integer capacity;

    private LocalDate occupancyStart;

    private LocalDate occupancyEnd;

    private Long hotelId;

    private Integer pageSize;

    private Integer pageNumber;
}
