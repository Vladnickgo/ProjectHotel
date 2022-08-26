package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.RoomTypeDto;

import java.util.List;

public interface RoomTypeService {
    RoomTypeDto getRoomTypeById(Integer id);

    List<RoomTypeDto> findAll();
}
