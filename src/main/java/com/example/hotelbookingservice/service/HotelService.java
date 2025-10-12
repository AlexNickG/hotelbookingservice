package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.entity.Hotel;
import com.example.hotelbookingservice.exception.EntityNotFoundException;
import com.example.hotelbookingservice.repository.HotelRepository;
import com.example.hotelbookingservice.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository repository;

    public List<Hotel> findAll() {
        return repository.findAll();
    }

    public Hotel findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Hotel with id {0} not found", id)));
    }

    public Hotel create(Hotel hotel) {
        return repository.save(hotel);
    }

    public Hotel update(Long id, Hotel hotel) {
        Hotel existedHotel = findById(id);
        if (existedHotel != null) {
            BeanUtils.copyNonNullProperties(hotel, existedHotel);
            return repository.save(existedHotel);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
