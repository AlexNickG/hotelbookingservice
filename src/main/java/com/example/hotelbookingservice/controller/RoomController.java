package com.example.hotelbookingservice.controller;

import com.example.hotelbookingservice.dto.RoomResponse;
import com.example.hotelbookingservice.dto.UpsertRoomRequest;
import com.example.hotelbookingservice.mapper.RoomMapper;
import com.example.hotelbookingservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private RoomService service;

    private RoomMapper roomMapper;

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(roomMapper.roomToResponse(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@RequestBody UpsertRoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomMapper.roomToResponse(service.create(roomMapper.requestToRoom(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(@PathVariable Long id, @RequestBody UpsertRoomRequest request) {
        return ResponseEntity.ok()
                .body(roomMapper.roomToResponse(service.update(id, roomMapper.requestToRoom(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
