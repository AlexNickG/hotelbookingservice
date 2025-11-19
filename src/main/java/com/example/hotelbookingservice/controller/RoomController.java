package com.example.hotelbookingservice.controller;

import com.example.hotelbookingservice.dto.RoomFilter;
import com.example.hotelbookingservice.dto.RoomListResponse;
import com.example.hotelbookingservice.dto.RoomResponse;
import com.example.hotelbookingservice.dto.UpsertRoomRequest;
import com.example.hotelbookingservice.mapper.RoomMapper;
import com.example.hotelbookingservice.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    private final RoomMapper roomMapper;

    @GetMapping
    public ResponseEntity<RoomListResponse> findAll(@Valid RoomFilter filter) {
        return ResponseEntity.ok(roomMapper.roomListToRoomListResponse(roomService.findAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(roomMapper.roomToResponse(roomService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@RequestBody UpsertRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomMapper.roomToResponse(roomService.create(roomMapper.requestToRoom(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(@PathVariable Long id, @RequestBody UpsertRoomRequest request) {
        return ResponseEntity.ok()
                .body(roomMapper.roomToResponse(roomService.update(id, roomMapper.requestToRoom(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
