package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.RoomStatusDao;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.service.RoomStatusService;
import com.vladnickgofj.hotel.service.mapper.Mapper;

import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.ROOM_STATUS_NOT_FOUND;

public class RoomStatusServiceImpl implements RoomStatusService {
    RoomStatusDao roomStatusRepository;
    Mapper<RoomStatusDto, RoomStatus> mapper;

    public RoomStatusServiceImpl(RoomStatusDao roomStatusRepository, Mapper<RoomStatusDto, RoomStatus> mapper) {
        this.roomStatusRepository = roomStatusRepository;
        this.mapper = mapper;
    }

    @Override
    public RoomStatusDto getRoomStatusById(Integer id) {
        RoomStatus roomStatus = roomStatusRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(ROOM_STATUS_NOT_FOUND, id)));
        return mapper.mapEntityToDto(roomStatus);
    }
}
