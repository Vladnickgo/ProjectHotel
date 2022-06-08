package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.RoomTypeDto;
import com.vladnickgofj.hotel.dao.entity.RoomType;

public class RoomTypeMapper implements Mapper<RoomTypeDto, RoomType>{

    @Override
    public RoomType mapDtoToEntity(RoomTypeDto roomTypeDto) {
        return RoomType.newBuilder()
                .id(roomTypeDto.getTypeId())
                .typeName(roomTypeDto.getTypeName())
                .build();
    }

    @Override
    public RoomTypeDto mapEntityToDto(RoomType roomType) {
        return RoomTypeDto.newBuilder()
                .typeId(roomType.getId())
                .typeName(roomType.getTypeName())
                .build();
    }
}
