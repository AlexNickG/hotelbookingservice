package com.example.hotelbookingservice.dto;

import lombok.Data;

@Data
public class UpsertUserRequest {

    private String name;

    private String password;

    private String email;

    private String role;
}
