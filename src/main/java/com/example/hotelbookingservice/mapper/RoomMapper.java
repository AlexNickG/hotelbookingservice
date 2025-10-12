package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.dto.RoomResponse;
import com.example.hotelbookingservice.dto.UpsertRoomRequest;
import com.example.hotelbookingservice.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    Room requestToRoom(UpsertRoomRequest request);

    Room requestToRoom(Long id, UpsertRoomRequest request);

    RoomResponse roomToResponse(Room room);

}
