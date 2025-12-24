package com.example.hotelbookingservice.kafka.controller;

import com.example.hotelbookingservice.kafka.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/stat")
@RequiredArgsConstructor
public class KafkaController {

    private final StatisticService statisticService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void getAllRegisters() throws IOException {
        statisticService.downloadStatistics();
    }
}
