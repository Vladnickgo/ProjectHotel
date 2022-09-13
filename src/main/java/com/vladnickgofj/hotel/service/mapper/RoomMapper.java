package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.RoomDto;
import com.vladnickgofj.hotel.controller.dto.RoomTypeDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomType;

public class RoomMapper implements Mapper<com.vladnickgofj.hotel.controller.dto.RoomDto, Room> {

    @Override
    public Room mapDtoToEntity(RoomDto roomDto) {
        return Room.newBuilder()
                .id(roomDto.getId())
                .roomType(getRoomType(roomDto))
                .numberOfBeds(roomDto.getNumberOfBeds())
                .price(roomDto.getPrice())
                .hotel(getHotel(roomDto))
                .build();
    }

    @Override
    public RoomDto mapEntityToDto(Room room) {
        return RoomDto.newBuilder()
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

    private Hotel getHotel(RoomDto roomDto) {
        return Hotel.newBuilder()
                .id(roomDto == null ? null : roomDto.getHotel().getId())
                .build();
    }

    private RoomType getRoomType(RoomDto roomDto) {
        return RoomType.newBuilder()
                .id(roomDto == null ? null : roomDto.getRoomType().getTypeId())
                .build();
    }
}