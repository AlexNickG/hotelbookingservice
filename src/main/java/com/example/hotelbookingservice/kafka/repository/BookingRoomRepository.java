package com.example.hotelbookingservice.kafka.repository;

import com.example.hotelbookingservice.kafka.model.BookingRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRoomRepository extends MongoRepository<BookingRoom, String> {
}
