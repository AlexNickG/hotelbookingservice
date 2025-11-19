package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.dto.UpsertBookingRequest;
import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BookingMapperDelegate implements BookingMapper {

    @Autowired
    private RoomService roomService;

    @Override
    public Booking requestToBooking(UpsertBookingRequest request) {
        Booking booking = new Booking();
        booking.setRoom(roomService.findById(request.getRoomId()));
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        return booking;
    }
}
