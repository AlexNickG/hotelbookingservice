package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.dto.RoomResponse;
import com.example.hotelbookingservice.dto.UpsertRoomRequest;
import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RoomMapperDelegate implements RoomMapper {

    @Autowired
    private HotelService hotelService;

    @Override
    public Room requestToRoom(UpsertRoomRequest request) {
        Room room = new Room();
        room.setHotel(hotelService.findById(request.getHotelId()));
        room.setName(request.getName());
        room.setDescription(request.getDescription());
        room.setNumber(request.getNumber());
        room.setCost(request.getCost());
        room.setCapacity(request.getCapacity());
        return room;
    }

//    @Override
//    public RoomResponse roomToResponse(Room room) {
//        RoomResponse response = new RoomResponse();
//        response.setHotelId(room.getHotel().getId());
//        response.setName(room.getName());
//        response.setDescription(room.getDescription());
//        response.setNumber(room.getNumber());
//        response.setCost(room.getCost());
//        response.setCapacity(room.getCapacity());
//        return null;
//    }
}
