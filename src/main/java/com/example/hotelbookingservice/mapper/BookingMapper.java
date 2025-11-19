package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.dto.BookingListResponse;
import com.example.hotelbookingservice.dto.BookingResponse;
import com.example.hotelbookingservice.dto.UpsertBookingRequest;
import com.example.hotelbookingservice.entity.Booking;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(BookingMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {


    Booking requestToBooking(UpsertBookingRequest request);

    @Mapping(target = "userId", expression = "java(booking.getUser().getId())")
    @Mapping(target = "roomId", expression = "java(booking.getRoom().getId())")
    BookingResponse bookingToResponse(Booking booking);

    List<BookingResponse> bookingListToBookingResponseList(List<Booking> bookingList);

    default BookingListResponse bookingListToBookingListResponse(List<Booking> bookingList) {
        BookingListResponse response = new BookingListResponse();
        response.setBookingResponseList(bookingListToBookingResponseList(bookingList));
        return response;
    }
}
