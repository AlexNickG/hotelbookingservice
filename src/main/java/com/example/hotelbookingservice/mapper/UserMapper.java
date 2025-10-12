package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.dto.UpsertUserRequest;
import com.example.hotelbookingservice.dto.UserResponse;
import com.example.hotelbookingservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(UpsertUserRequest request);

    User requestToUser(Long id, UpsertUserRequest request);

    UserResponse userToResponse(User user);
}
