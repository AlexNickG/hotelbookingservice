package com.example.hotelbookingservice.kafka.service;

import com.example.hotelbookingservice.kafka.repository.BookingRoomRepository;
import com.example.hotelbookingservice.kafka.repository.KafkaRepository;
import com.example.hotelbookingservice.kafka.model.BookingRoom;
import com.example.hotelbookingservice.kafka.model.KafkaMessage;
import com.example.hotelbookingservice.kafka.model.RegisterUser;
import com.example.hotelbookingservice.kafka.repository.RegisterUserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final RegisterUserRepository registerUserRepository;

    private final BookingRoomRepository bookingRoomRepository;

    private final KafkaRepository kafkaRepository;

    private final StatisticService statisticService;

    private final ObjectMapper objectMapper;

    public void saveMessage(KafkaMessage message) {
        Map<String,Object> payload = objectMapper.convertValue(message, new TypeReference<>(){});
        KafkaMessage m = new KafkaMessage();
        m.setType("TEXT");
        m.setPayload(payload);
        kafkaRepository.save(m);
    }

    public void addRegister(RegisterUser registerUser) {
        Map<String,Object> payload = objectMapper.convertValue(registerUser, new TypeReference<>(){});
        KafkaMessage m = new KafkaMessage();
        m.setType("User registered");
        m.setPayload(payload);
        kafkaRepository.save(m);
        //registerUserRepository.save(registerUser);
    }

    public void addBooking(BookingRoom bookingRoom) {
        Map<String,Object> payload = objectMapper.convertValue(bookingRoom, new TypeReference<>(){});
        KafkaMessage m = new KafkaMessage();
        m.setType("Room booked");
        m.setPayload(payload);
        kafkaRepository.save(m);
        //bookingRoomRepository.save(bookingRoom);
    }

    public List<BookingRoom> readRegistersStatistics() {
        return bookingRoomRepository.findAll();
    }

    public void downloadRegisterStatistics() throws IOException {
        statisticService.writeBookingRoomsToCsv(readRegistersStatistics());
    }
}
