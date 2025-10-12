package com.example.hotelbookingservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HotelListResponse {

    private List<HotelResponse> hotels = new ArrayList<>();

    //TODO: Опишите DTO HTTP-ответа, который будет использоваться в слое
    // контроллеров для возвращения списка объектов (других DTO) с пагинацией.

}
