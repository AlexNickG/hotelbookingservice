package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.dto.RoomFilter;
import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.exception.EntityNotFoundException;
import com.example.hotelbookingservice.repository.RoomRepository;
import com.example.hotelbookingservice.repository.RoomSpecification;
import com.example.hotelbookingservice.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repository;

    public List<Room> findAll(RoomFilter filter) {
        return repository.findAll(RoomSpecification.withFilter(filter));
    }

    public Room findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Room with id {0} not found", id)));
    }

    public Room create(Room room) {
        return repository.save(room);
    }

    public Room update(Long id, Room room) {
        Room existedRoom = findById(id);
        if (existedRoom != null) {
            BeanUtils.copyNonNullProperties(room, existedRoom);
            return repository.save(existedRoom);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
