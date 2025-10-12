package com.example.hotelbookingservice.dto;

import lombok.Data;

@Data
public class HotelResponse {

    private Long id;

    private String name;

    private String adTitle;

    private String hotelCity;

    private Integer distance;

    private short rating;

    private Integer ratingsNum;
}
