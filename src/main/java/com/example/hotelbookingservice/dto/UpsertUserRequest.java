package com.example.hotelbookingservice.dto;

import lombok.Data;

@Data
public class UpsertUserRequest {

    private String username;

    private String password;

    private String email;

    //private String role;
}
