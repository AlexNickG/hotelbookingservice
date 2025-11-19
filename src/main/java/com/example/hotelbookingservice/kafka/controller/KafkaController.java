package com.example.hotelbookingservice.kafka.controller;

import com.example.hotelbookingservice.kafka.model.RegisterUser;
import com.example.hotelbookingservice.kafka.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/stat")
@RequiredArgsConstructor
public class KafkaController {

    @Value("${app.kafka.registerUserTopic}")
    private String registerUserTopicName;

    private final KafkaService kafkaService;

    private final KafkaTemplate<String, RegisterUser> kafkaTemplate;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void getAllRegisters() throws IOException {
        kafkaService.downloadRegisterStatistics();
    }
}
