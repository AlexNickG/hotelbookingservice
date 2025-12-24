package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.entity.UnavailableDate;
import com.example.hotelbookingservice.exception.NotAvailableRoomException;
import com.example.hotelbookingservice.kafka.mapper.EventMapper;
import com.example.hotelbookingservice.kafka.model.KafkaMessage;
import com.example.hotelbookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final EventMapper eventMapper;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @Value("${app.kafka.bookingRoomTopic}")
    private String topicName;

    private final RoomService roomService;

    @Transactional
    public Booking toBookARoom(Booking booking) {
        if (!checkRoomAccessibility(booking)) {
            Room room = booking.getRoom();
            UnavailableDate unavailable = UnavailableDate.builder()
                    .startOccupancy(booking.getCheckIn())
                    .endOccupancy(booking.getCheckOut())
                    .room(room)
                    .build();
            room.getOccupancyDates().add(unavailable);
            roomService.update(room.getId(), room);
            booking.setRoom(room);
            Booking newBooking = bookingRepository.save(booking);
            kafkaTemplate.send(topicName, eventMapper.bookingToKafkaMessage(newBooking));
            return newBooking;
        }
        throw new NotAvailableRoomException("Room is not available for the requested period");
    }

    public List<Booking> getBookedRoomsList() {
        return bookingRepository.findAll();
    }

    public Boolean checkRoomAccessibility(Booking booking) {
        Room room = booking.getRoom();
        if (room.getOccupancyDates().isEmpty()) return false;
        return room.getOccupancyDates().stream().anyMatch(u ->
                !u.getEndOccupancy().isBefore(booking.getCheckIn()) && !u.getStartOccupancy().isAfter(booking.getCheckOut())
        );
    }
}
