package com.example.hotelbookingservice.controller;

import com.example.hotelbookingservice.dto.UpsertUserRequest;
import com.example.hotelbookingservice.dto.UserResponse;
import com.example.hotelbookingservice.entity.Role;
import com.example.hotelbookingservice.entity.RoleType;
import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.mapper.UserMapper;
import com.example.hotelbookingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UpsertUserRequest request, @RequestParam RoleType roleType) {
        User newUser = userService.save(userMapper.requestToUser(request), Role.from(roleType));
        return ResponseEntity.ok().body(userMapper.userToResponse(newUser));
    }
}
