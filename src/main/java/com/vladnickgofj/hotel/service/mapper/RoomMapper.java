package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.context.ApplicationContextInjector;
import com.vladnickgofj.hotel.controller.dto.RoomDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.entity.RoomType;
import com.vladnickgofj.hotel.service.HotelService;
import com.vladnickgofj.hotel.service.RoomStatusService;
import com.vladnickgofj.hotel.service.RoomTypeService;

public class RoomMapper implements Mapper<RoomDto, Room> {

    @Override
    public Room mapDtoToEntity(RoomDto roomDto) {
        HotelService hotelService = ApplicationContextInjector.getInstance().getHotelService();
        RoomStatusService roomStatusService = ApplicationContextInjector.getInstance().getRoomStatusService();
        RoomTypeService roomTypeService = ApplicationContextInjector.getInstance().getRoomTypeService();

        return Room.newBuilder()
                .id(roomDto.getId())
                .roomType(getRoomType(roomDto, roomTypeService))
                .numberOfBeds(roomDto.getNumberOfBeds())
                .roomStatus(getRoomStatus(roomDto, roomStatusService))
                .price(roomDto.getPrice())
                .hotel(getHotel(roomDto, hotelService))
                .build();
    }

    @Override
    public RoomDto mapEntityToDto(Room room) {
        return RoomDto.newBuilder()
                .id(room.getId())
                .roomTypeId(room.getRoomType().getId())
                .numberOfBeds(room.getNumberOfBeds())
                .roomStatusId(room.getRoomStatus().getId())
                .price(room.getPrice())
                .hotelId(room.getHotel().getId())
                .build();
    }

    private Hotel getHotel(RoomDto roomDto, HotelService hotelService) {
        return Hotel.newBuilder()
                .id(roomDto.getHotelId())
                .name(hotelService.getHotelById(roomDto.getHotelId()).getName())
                .build();
    }

    private RoomStatus getRoomStatus(RoomDto roomDto, RoomStatusService roomStatusService) {
        return RoomStatus.newBuilder()
                .id(roomDto.getRoomStatusId())
                .name(roomStatusService.getRoomStatusById(roomDto.getRoomStatusId()).getStatusName())
                .build();
    }

    private RoomType getRoomType(RoomDto roomDto, RoomTypeService roomTypeService) {
        return RoomType.newBuilder()
                .id(roomDto.getRoomTypeId())
                .typeName(roomTypeService.getRoomTypeById(roomDto.getRoomTypeId()).getTypeName())
                .build();
    }
}
