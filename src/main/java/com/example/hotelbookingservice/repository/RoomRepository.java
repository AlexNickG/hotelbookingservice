package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    @Override
    Page<Room> findAll(Pageable pageable);
}
