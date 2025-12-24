package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.dto.HotelListResponse;
import com.example.hotelbookingservice.dto.HotelResponse;
import com.example.hotelbookingservice.dto.UpsertHotelRequest;
import com.example.hotelbookingservice.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

    Hotel requestToHotel(UpsertHotelRequest request);

    HotelResponse hotelToResponse(Hotel hotel);

    List<HotelResponse> hotelListToHotelResponseList(List<Hotel> hotelList);

    default HotelListResponse hotelListToHotelListResponse(List<Hotel> hotelList) {
        HotelListResponse response = new HotelListResponse();
        response.setHotels(hotelListToHotelResponseList(hotelList));

        return response;
    }
}
