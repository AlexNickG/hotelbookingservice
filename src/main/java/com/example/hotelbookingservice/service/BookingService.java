package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.entity.UnavailableDate;
import com.example.hotelbookingservice.kafka.mapper.EventMapper;
import com.example.hotelbookingservice.kafka.model.BookingRoom;
import com.example.hotelbookingservice.kafka.model.RegisterUser;
import com.example.hotelbookingservice.kafka.service.KafkaService;
import com.example.hotelbookingservice.mapper.BookingMapper;
import com.example.hotelbookingservice.repository.BookingRepository;
import com.example.hotelbookingservice.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    private final EventMapper eventMapper;

    private final KafkaTemplate<String, BookingRoom> kafkaTemplate;

    @Value("${app.kafka.bookingRoomTopic}")
    private String topicName;

    private final RoomService roomService;

    private final KafkaService kafkaService;
    private final RoomRepository roomRepository;

    @Transactional
    public Booking toBookARoom(Booking booking) {
        if (!checkRoomAccessibility(booking)) {
            Room room = booking.getRoom();
            //TODO: 1. Проверить, свободна ли комната отеле на указанные даты.
            UnavailableDate unavailable = UnavailableDate.builder()
                    .startOccupancy(booking.getCheckIn())
                    .endOccupancy(booking.getCheckOut())
                    .room(room)
                    .build();
            room.getOccupancyDates().add(unavailable);
            roomService.update(room.getId(), room);
            booking.setRoom(room);
            //2. Добавить забронированные даты в список занятых дат комнаты.
            Booking newBooking = bookingRepository.save(booking);
            kafkaTemplate.send(topicName, eventMapper.bookingToBookingRoom(newBooking));
            //kafkaService.addBooking(eventMapper.bookingToBookingRoom(newBooking));
            return newBooking;
        }
        throw new IllegalStateException("Room is not available for the requested period");//TODO: Выбрасывать другое исключение
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

//        Room room = booking.getRoom();
//        Set<UnavailableDate> unavailableDates = room.getOccupancyDates();
//        LocalDate checkIn = booking.getCheckIn();
//        LocalDate checkOut = booking.getCheckOut();
//        if (checkIn == null || checkOut == null) return false;
//        if (checkIn.isAfter(checkOut)) return false;
//
//        // считаем интервалы включительно: [start, end]
//        for (UnavailableDate d : unavailableDates) {
//            LocalDate start = d.getStartOccupancy();
//            LocalDate end = d.getEndOccupancy();
//            if (start == null || end == null) continue;
//
//            boolean noOverlap = checkOut.isBefore(start) || end.isBefore(checkIn);
//            if (!noOverlap) {
//                // найдено пересечение
//                return false;
//            }
//        }
//        return true;
    }
}
