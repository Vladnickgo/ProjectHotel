package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.dao.entity.RoomType;

public class RoomTypeMapper implements Mapper<com.vladnickgofj.hotel.controller.dto.RoomTypeDto, RoomType>{

    @Override
    public RoomType mapDtoToEntity(com.vladnickgofj.hotel.controller.dto.RoomTypeDto roomTypeDto) {
        return RoomType.newBuilder()
                .id(roomTypeDto.getTypeId())
                .typeName(roomTypeDto.getTypeName())
                .build();
    }

    @Override
    public com.vladnickgofj.hotel.controller.dto.RoomTypeDto mapEntityToDto(RoomType roomType) {
        return com.vladnickgofj.hotel.controller.dto.RoomTypeDto.newBuilder()
                .typeId(roomType.getId())
                .typeName(roomType.getTypeName())
                .build();
    }
}
