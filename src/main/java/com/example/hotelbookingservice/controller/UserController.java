package com.example.hotelbookingservice.controller;

import com.example.hotelbookingservice.dto.UpsertUserRequest;
import com.example.hotelbookingservice.dto.UserListResponse;
import com.example.hotelbookingservice.dto.UserResponse;
import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.mapper.UserMapper;
import com.example.hotelbookingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")//эта аннотация влияет на работу
    public UserListResponse getAllUsers() {
        return userMapper.userListToUserListResponse(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        if (!user.getId().equals(id) && !user.getUsername().equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userMapper.userToResponse(userService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UpsertUserRequest request,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        User existedUser = userService.findByUsername(userDetails.getUsername());
        if (!existedUser.getId().equals(id) && !existedUser.getUsername().equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userMapper.userToResponse(userService.update(userMapper.requestToUser(id, request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User existedUser = userService.findByUsername(userDetails.getUsername());
        if (!existedUser.getId().equals(id) && !existedUser.getUsername().equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
