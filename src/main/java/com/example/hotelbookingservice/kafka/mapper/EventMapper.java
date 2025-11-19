package com.example.hotelbookingservice.kafka.mapper;

import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.kafka.model.BookingRoom;
import com.example.hotelbookingservice.kafka.model.RegisterUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    @Mapping(source = "id", target = "userId")
    RegisterUser userToRegisterUser(User user);

    @Mapping(target = "userId", expression = "java(booking.getUser().getId())")
    BookingRoom bookingToBookingRoom(Booking booking);
}
