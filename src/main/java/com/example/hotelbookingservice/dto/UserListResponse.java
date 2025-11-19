package com.example.hotelbookingservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserListResponse {

    private List<UserResponse> userResponseList = new ArrayList<>();
}
