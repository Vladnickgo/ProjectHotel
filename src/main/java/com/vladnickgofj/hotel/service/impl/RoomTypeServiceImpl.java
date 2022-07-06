package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.RoomTypeDto;
import com.vladnickgofj.hotel.dao.RoomTypeDao;
import com.vladnickgofj.hotel.dao.entity.RoomType;
import com.vladnickgofj.hotel.service.RoomTypeService;
import com.vladnickgofj.hotel.service.mapper.Mapper;

import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.ROOM_TYPE_NOT_DEFINED;

public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeDao roomTypeRepository;
    private final Mapper<RoomTypeDto, RoomType> mapper;

    public RoomTypeServiceImpl(RoomTypeDao roomTypeRepository, Mapper<RoomTypeDto, RoomType> mapper) {
        this.roomTypeRepository = roomTypeRepository;
        this.mapper = mapper;
    }

    @Override
    public RoomTypeDto getRoomTypeById(Integer id) {
        RoomType roomType = roomTypeRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(ROOM_TYPE_NOT_DEFINED, id)));
        return mapper.mapEntityToDto(roomType);
    }
}
