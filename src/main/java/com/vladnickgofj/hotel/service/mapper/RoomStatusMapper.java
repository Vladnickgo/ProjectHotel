package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.RoomDtoResponse;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.entity.*;

public class RoomStatusMapper implements Mapper<RoomStatusDto, RoomStatus> {
    @Override
    public RoomStatus mapDtoToEntity(RoomStatusDto roomStatusDto) {
        return RoomStatus.newBuilder()
                .id(roomStatusDto.getStatusId())
                .dateStart(roomStatusDto.getDateStart())
                .dateEnd(roomStatusDto.getDateEnd())
                .room(Room.newBuilder()
                        .id(roomStatusDto.getRoomDtoResponse().getId())
                        .roomType(RoomType.newBuilder()
                                .typeName(roomStatusDto.getRoomDtoResponse().getRoomType().getTypeName())
                                .build())
                        .price(roomStatusDto.getNumberOfNights())
                        .hotel(Hotel.newBuilder()
                                .name(roomStatusDto.getRoomDtoResponse().getHotel().getName())
                                .build())
                        .build())
                .statusStatement(StatusStatement.newBuilder()
                        .statusStatementId(roomStatusDto.getStatusStatement().getStatusStatementId())
                        .build())
                .build();
    }

    @Override
    public RoomStatusDto mapEntityToDto(RoomStatus roomStatus) {
        return RoomStatusDto.newBuilder()
                .statusId(roomStatus.getId())
                .dateStart(roomStatus.getDateStart())
                .dateEnd(roomStatus.getDateEnd())
                .roomDtoResponse(RoomDtoResponse.newBuilder()
                        .id(roomStatus.getRoom().getId())
                        .roomType(com.vladnickgofj.hotel.controller.dto.RoomTypeDto.newBuilder()
                                .typeId(roomStatus.getRoom().getRoomType().getId())
                                .typeName(roomStatus.getRoom().getRoomType().getTypeName())
                                .build())
                        .numberOfBeds(roomStatus.getRoom().getNumberOfBeds())
                        .price(roomStatus.getRoom().getPrice())
                        .hotel(HotelDto.newBuilder()
                                .id(roomStatus.getRoom().getHotel().getId())
                                .name(roomStatus.getRoom().getHotel().getName())
                                .build())
                        .build())
                .statusStatement(StatusStatement.newBuilder()
                        .statusStatementId(roomStatus.getStatusStatement().getStatusStatementId())
                        .statusStatementName(roomStatus.getStatusStatement().getStatusStatementName())
                        .build())
                .build();
    }
}
