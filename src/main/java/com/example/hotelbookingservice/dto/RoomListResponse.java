package com.example.hotelbookingservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoomListResponse {

    private List<RoomResponse> roomsList = new ArrayList<>();
}
