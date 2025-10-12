package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
