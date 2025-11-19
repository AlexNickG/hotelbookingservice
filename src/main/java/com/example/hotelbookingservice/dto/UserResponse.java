package com.example.hotelbookingservice.dto;

import com.example.hotelbookingservice.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private Long id;

    private String username;

    //private String password;

    private String email;

    //private List<Role> roles;
}
