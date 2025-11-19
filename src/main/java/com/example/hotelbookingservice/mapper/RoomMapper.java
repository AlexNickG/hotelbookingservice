package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.dto.RoomListResponse;
import com.example.hotelbookingservice.dto.RoomResponse;
import com.example.hotelbookingservice.dto.UpsertRoomRequest;
import com.example.hotelbookingservice.entity.Room;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(RoomMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    Room requestToRoom(UpsertRoomRequest request);

    Room requestToRoom(Long id, UpsertRoomRequest request);

    @Mapping(target = "hotelId", expression = "java(room.getHotel().getId())")
    RoomResponse roomToResponse(Room room);

    List<RoomResponse> roomListToResponseList(List<Room> roomList);

    default RoomListResponse roomListToRoomListResponse(List<Room> roomList) {
        RoomListResponse response = new RoomListResponse();

        response.setRoomsList(roomListToResponseList(roomList));
        return response;
    }
}
