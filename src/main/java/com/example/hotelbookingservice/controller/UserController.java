package com.example.hotelbookingservice.controller;

import com.example.hotelbookingservice.dto.UpsertUserRequest;
import com.example.hotelbookingservice.dto.UserResponse;
import com.example.hotelbookingservice.mapper.UserMapper;
import com.example.hotelbookingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    private final UserMapper userMapper;

    @PostMapping
    private ResponseEntity<UserResponse> create(UpsertUserRequest request) {

        return ResponseEntity.ok().body(userMapper.userToResponse(service.create(userMapper.requestToUser(request))));
    }
}
