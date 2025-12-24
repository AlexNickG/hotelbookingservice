//package com.example.hotelbookingservice.kafka.service;
//
//import com.example.hotelbookingservice.kafka.repository.BookingRoomRepository;
//import com.example.hotelbookingservice.kafka.repository.KafkaMessageRepository;
//import com.example.hotelbookingservice.kafka.model.BookingRoom;
//import com.example.hotelbookingservice.kafka.model.KafkaMessage;
//import com.example.hotelbookingservice.kafka.model.RegisterUser;
//import com.example.hotelbookingservice.kafka.repository.RegisterUserRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class KafkaService {
//
//    private final RegisterUserRepository registerUserRepository;
//
//    private final BookingRoomRepository bookingRoomRepository;
//
//    private final KafkaMessageRepository kafkaMessageRepository;
//
//    private final StatisticService statisticService;
//
//    private final ObjectMapper objectMapper;
//
//    public void saveMessage(KafkaMessage message) {
//        //Map<String,Object> payload = objectMapper.convertValue(message, new TypeReference<>(){});
//        //KafkaMessage m = new KafkaMessage();
//        //m.setType("TEXT");
//        //m.setPayload(payload);
//        kafkaMessageRepository.save(message);
//    }
//
//   // public void addRegister(RegisterUser registerUser) {
//        //Map<String,Object> payload = objectMapper.convertValue(registerUser, new TypeReference<>(){});
//        //KafkaMessage m = new KafkaMessage();
//        //m.setType("User registered");
//        //m.setPayload(payload);
//        //kafkaRepository.save(m);
//   //     registerUserRepository.save(registerUser);
//  //  }
//
//   // public void addBooking(BookingRoom bookingRoom) {
////        Map<String,Object> payload = objectMapper.convertValue(bookingRoom, new TypeReference<>(){});
////        KafkaMessage m = new KafkaMessage();
////        m.setType("Room booked");
////        m.setPayload(payload);
//        //kafkaRepository.save(m);
//     //   bookingRoomRepository.save(bookingRoom);
//   // }
//
//
//
//
//}
