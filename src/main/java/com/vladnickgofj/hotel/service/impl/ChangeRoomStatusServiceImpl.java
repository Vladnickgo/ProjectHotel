package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.RoomStatusDao;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.service.ChangeRoomStatusService;
import com.vladnickgofj.hotel.service.mapper.Mapper;

public class ChangeRoomStatusServiceImpl implements ChangeRoomStatusService {
    private final RoomStatusDao roomStatusRepository;
    private final Mapper<RoomStatusDto, RoomStatus> roomStatusMapper;
    private final Mapper<BookingDto, Booking> bookingMapper;

    public ChangeRoomStatusServiceImpl(RoomStatusDao roomStatusRepository, Mapper<RoomStatusDto, RoomStatus> roomStatusMapper, Mapper<BookingDto, Booking> bookingMapper) {
        this.roomStatusRepository = roomStatusRepository;
        this.roomStatusMapper = roomStatusMapper;
        this.bookingMapper = bookingMapper;
    }


    @Override
    public void changeRoomStatusDto(RoomStatusDto roomStatusDto, BookingDto bookingDto) {
        RoomStatus roomStatus=roomStatusMapper.mapDtoToEntity(roomStatusDto);
        Booking booking=bookingMapper.mapDtoToEntity(bookingDto);
        roomStatusRepository.changeRoomStatus(roomStatus,booking);
    }
}
