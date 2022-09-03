package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.RoomDtoResponse;
import com.vladnickgofj.hotel.controller.dto.RoomTypeDto;
import com.vladnickgofj.hotel.dao.entity.Room;

public class RoomResponseMapper implements Mapper<RoomDtoResponse, Room> {
    @Override
    public Room mapDtoToEntity(RoomDtoResponse roomDtoResponse) {
        return null;
    }

    @Override
    public RoomDtoResponse mapEntityToDto(Room room) {
        return RoomDtoResponse.newBuilder()
                .id(room.getId())
                .roomType(RoomTypeDto.newBuilder()
                        .typeId(room.getRoomType().getId())
                        .typeName(room.getRoomType().getTypeName())
                        .build())
                .numberOfBeds(room.getNumberOfBeds())
                .price(room.getPrice())
                .hotel(HotelDto.newBuilder()
                        .id(room.getHotel().getId())
                        .name(room.getHotel().getName())
                        .build())
                .build();
    }
}
