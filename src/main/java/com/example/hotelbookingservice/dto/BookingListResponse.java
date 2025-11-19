package com.example.hotelbookingservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookingListResponse {

    private List<BookingResponse> bookingResponseList = new ArrayList<>();
}
