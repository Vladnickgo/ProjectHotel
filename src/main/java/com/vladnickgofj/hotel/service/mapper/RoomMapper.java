package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomType;

public class RoomMapper implements Mapper<com.vladnickgofj.hotel.controller.dto.RoomDto, Room> {

    @Override
    public Room mapDtoToEntity(com.vladnickgofj.hotel.controller.dto.RoomDto roomDto) {
        return Room.newBuilder()
                .id(roomDto.getId())
                .roomType(getRoomType(roomDto))
                .numberOfBeds(roomDto.getNumberOfBeds())
                .price(roomDto.getPrice())
                .hotel(getHotel(roomDto))
                .build();
    }

    @Override
    public com.vladnickgofj.hotel.controller.dto.RoomDto mapEntityToDto(Room room) {
        return com.vladnickgofj.hotel.controller.dto.RoomDto.newBuilder()
                .id(room.getId())
                .roomTypeId(room.getRoomType().getId())
                .numberOfBeds(room.getNumberOfBeds())
                .price(room.getPrice())
                .hotelId(room.getHotel().getId())
                .build();
    }

    private Hotel getHotel(com.vladnickgofj.hotel.controller.dto.RoomDto roomDto) {
        return Hotel.newBuilder()
                .id(roomDto == null ? null : roomDto.getHotelId())
                .build();
    }

    private RoomType getRoomType(com.vladnickgofj.hotel.controller.dto.RoomDto roomDto) {
        return RoomType.newBuilder()
                .id(roomDto == null ? null : roomDto.getRoomTypeId())
                .build();
    }
}