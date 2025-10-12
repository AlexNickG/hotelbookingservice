package com.example.hotelbookingservice.dto;

import lombok.Data;

@Data
public class UpsertHotelRequest {

    private String name;

    private String adTitle;

    private String hotelCity;

    private Integer distance;
}
