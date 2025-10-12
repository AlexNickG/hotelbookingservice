package com.example.hotelbookingservice.dto;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String name;

    private String password;

    private String email;

    private String role;
}
