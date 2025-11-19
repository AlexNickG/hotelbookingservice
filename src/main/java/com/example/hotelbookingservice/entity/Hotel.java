package com.example.hotelbookingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "ad_title")
    private String adTitle;

    @Column(name = "city")
    private String hotelCity;

    private String address;

    private Integer distance;

    private Float rating;

    @Column(name = "number_of_reviews")
    private Integer ratingsNum;
}
