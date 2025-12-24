package com.example.hotelbookingservice.kafka.mapper;

import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.kafka.model.KafkaMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    @Mapping(source = "id", target = "userId")
    KafkaMessage registerUserToKafkaMessage(User user);

    @Mapping(target = "userId", expression = "java(String.valueOf(booking.getUser().getId()))")
    KafkaMessage bookingToKafkaMessage(Booking booking);
}
