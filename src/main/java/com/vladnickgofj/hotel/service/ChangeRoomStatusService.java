package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;

public interface ChangeRoomStatusService {
    void changeRoomStatusDto(RoomStatusDto roomStatusDto, BookingDto bookingDto);
}
