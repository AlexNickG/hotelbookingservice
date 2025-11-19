package com.example.hotelbookingservice.dto;

import com.example.hotelbookingservice.validation.HotelFilterValid;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@HotelFilterValid
public class HotelFilter {

    private Long id;

    private String name;

    private String adTitle;

    private String hotelCity;

    private String address;

    private Integer distance;

    private Float rating;

    private Integer ratingsNum;

    private Integer pageSize;

    private Integer pageNumber;

}
