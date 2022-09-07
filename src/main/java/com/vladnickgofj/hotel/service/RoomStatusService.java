package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDtoRequest;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import com.vladnickgofj.hotel.service.util.RoomStatusDtoRequestUtil;

import java.time.LocalDate;
import java.util.List;

public interface RoomStatusService {
    RoomStatusDto getRoomStatusById(Integer id);

    List<RoomStatusDto> findAll(RoomStatusDtoRequestUtil roomStatusDtoRequest);

    Integer getNumberOfPages(RoomStatusDtoRequestUtil roomStatusDtoRequest);

    List<RoomStatusDto> findAllFreeByParameters(UsersOrderDto usersOrderDto);

    LocalDate findDateEndForFreeStatusByRoomIdAndDateStart(BookingDto byId);
}
