package com.example.hotelbookingservice.controller;

import com.example.hotelbookingservice.dto.HotelListResponse;
import com.example.hotelbookingservice.dto.HotelResponse;
import com.example.hotelbookingservice.dto.UpsertHotelRequest;
import com.example.hotelbookingservice.mapper.HotelMapper;
import com.example.hotelbookingservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;
    private final HotelMapper hotelMapper;

    @GetMapping
    public ResponseEntity<HotelListResponse> findAll() {
        return ResponseEntity.ok().body(hotelMapper.hotelListToHotelListResponse(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(hotelMapper.hotelToResponse(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<HotelResponse> create(@RequestBody UpsertHotelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelMapper.hotelToResponse(service.create(hotelMapper.requestToHotel(request))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> update(@PathVariable Long id, @RequestBody UpsertHotelRequest request) {
        return ResponseEntity.ok()
                .body(hotelMapper.hotelToResponse(service.update(id, hotelMapper.requestToHotel(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
