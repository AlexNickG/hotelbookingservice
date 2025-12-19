package com.example.hotelbookingservice.controller;

import com.example.hotelbookingservice.dto.*;
import com.example.hotelbookingservice.entity.Hotel;
import com.example.hotelbookingservice.mapper.HotelMapper;
import com.example.hotelbookingservice.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;
    private final HotelMapper hotelMapper;

    @GetMapping
    public ResponseEntity<HotelListResponse> findAll(@Valid HotelFilter filter) {
        return ResponseEntity.ok().body(hotelMapper.hotelListToHotelListResponse(service.findAll(filter)));
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

    @PatchMapping("/{id}")
    public HotelResponse changeHotelRating(@PathVariable Long id, @RequestBody UpsertHotelRatingRequest request) {

        return hotelMapper.hotelToResponse(service.changeHotelRating(id, request.getNewMark()));

    }
}
