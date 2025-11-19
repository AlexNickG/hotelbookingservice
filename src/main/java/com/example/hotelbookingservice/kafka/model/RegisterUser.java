package com.example.hotelbookingservice.kafka.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class RegisterUser {

    private Long userId;
}
