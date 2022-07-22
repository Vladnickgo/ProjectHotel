package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;

import java.util.List;

public interface RoomStatusDao extends CrudDao<RoomStatus, Integer> {
    List<RoomStatus> findAll(Integer hotelId, RoomStatusDto roomStatus, String roomStatusQuerySubstitute, Integer numberOnPage, Integer firstRoomOnPage, String sorting, String ordering);

    Integer countAll(Integer hotelId, String roomStatusQuerySubstitute, RoomStatusDto roomStatus);

    void changeRoomStatus(RoomStatus roomStatus, Booking booking);


}
