package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDtoRequest;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;

import java.time.LocalDate;
import java.util.List;

public interface RoomStatusService {
    RoomStatusDto getRoomStatusById(Integer id);

    List<RoomStatusDto> findAll(RoomStatusDtoRequest roomStatusDtoRequest);

    Integer getNumberOfPages(RoomStatusDtoRequest roomStatusDtoRequest);

    List<RoomStatusDto> findAllFreeByParameters(UsersOrderDto usersOrderDto);

    LocalDate findDateEndForFreeStatusByRoomIdAndDateStart(BookingDto byId);
}
