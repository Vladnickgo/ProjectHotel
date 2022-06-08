package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;

public class RoomStatusMapper implements Mapper<RoomStatusDto, RoomStatus>{
    @Override
    public RoomStatus mapDtoToEntity(RoomStatusDto roomStatusDto) {
        return RoomStatus.newBuilder()
                .id(roomStatusDto.getStatusId())
                .name(roomStatusDto.getStatusName())
                .build();
    }

    @Override
    public RoomStatusDto mapEntityToDto(RoomStatus roomStatus) {
        return RoomStatusDto.newBuilder()
                .statusId(roomStatus.getId())
                .statusName(roomStatus.getName())
                .build();
    }
}
