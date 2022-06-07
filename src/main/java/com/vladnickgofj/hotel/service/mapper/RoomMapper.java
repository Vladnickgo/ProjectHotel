package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.RoomDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.entity.RoomType;

public class RoomMapper implements Mapper<RoomDto, Room> {
    @Override
    public Room mapDtoToEntity(RoomDto roomDto) {
        return Room.newBuilder()
                .id(roomDto.getId())
                .roomType(RoomType.newBuilder()
                        .id(roomDto.getRoomTypeId())
                        .build())
                .numberOfBeds(roomDto.getNumberOfBeds())
                .roomStatus(RoomStatus.newBuilder()
                        .id(roomDto.getRoomStatusId())
                        .build())
                .price(roomDto.getPrice())
                .hotel(Hotel.newBuilder()
                        .id(roomDto.getHotelId())
                        .build())
                .build();
    }

    @Override
    public RoomDto mapEntityToDto(Room room) {
        return RoomDto.newBuilder()
                .id(room.getId())
                .roomTypeId(room.getId())
                .numberOfBeds(room.getNumberOfBeds())
                .roomStatusId(room.getRoomStatus().getId())
                .price(room.getPrice())
                .hotelId(room.getHotel().getId())
                .build();
    }
}
