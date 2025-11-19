package com.example.hotelbookingservice.dto;

import com.example.hotelbookingservice.validation.UserFilterValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@UserFilterValid
@AllArgsConstructor
public class UserFilter {

    private Integer pageSize;

    private Integer pageNumber;
}
