package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDtoRequest;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.service.util.RoomStatusDtoRequestUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface RoomStatusDao extends CrudDao<RoomStatus, Integer> {
    List<RoomStatus> findAll(RoomStatusDtoRequestUtil roomStatusDtoRequest, Integer firstRoomOnPage);

    Integer countAll(RoomStatusDtoRequestUtil roomStatusDtoRequest);

    List<RoomStatus> findAllFreeByParameters(UsersOrderDto usersOrderDto);

    LocalDate findFreeByRoomIdAndDateStart(BookingDto byId);
}
