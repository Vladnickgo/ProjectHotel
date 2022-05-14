package com.vladnickgofj.hotel.service.mapper;

public interface Mapper<DTO,ENTITY> {
    ENTITY mapDtoToEntity(DTO dto);
    DTO mapEntityToDto(ENTITY entity);
}
