package com.example.hotelbookingservice.controller;

import com.example.hotelbookingservice.dto.BookingListResponse;
import com.example.hotelbookingservice.dto.BookingResponse;
import com.example.hotelbookingservice.dto.UpsertBookingRequest;
import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.mapper.BookingMapper;
import com.example.hotelbookingservice.security.AppUserPrincipal;
import com.example.hotelbookingservice.service.BookingService;
import com.example.hotelbookingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    private final BookingMapper bookingMapper;

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public BookingListResponse getAllBookings() {

        return bookingMapper.bookingListToBookingListResponse(bookingService.getBookedRoomsList());
//        BookingListResponse responseList = new BookingListResponse();
//        BookingResponse response = new BookingResponse();
//        List<BookingResponse> bookingResponseList = bookingService.getBookedRoomsList().stream().map(booking -> {
//            response.setId(booking.getId());
//            response.setRoomId(booking.getRoom().getId());
//            response.setUserId(booking.getUser().getId());
//            response.setCheckIn(booking.getCheckIn());
//            response.setCheckOut(booking.getCheckOut());
//            return response;
//        }).toList();
//        responseList.setBookingResponseList(bookingResponseList);
        //return responseList;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(@RequestBody UpsertBookingRequest request,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        Booking newBooking = bookingMapper.requestToBooking(request);
        newBooking.setUser(userService.findByUsername(userDetails.getUsername()));
        return bookingMapper.bookingToResponse(bookingService.toBookARoom(newBooking));
    }
}
