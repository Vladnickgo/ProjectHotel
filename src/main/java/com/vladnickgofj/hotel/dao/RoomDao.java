package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.controller.dto.PaginateRoomDto;
import com.vladnickgofj.hotel.dao.entity.Room;

import java.util.List;

public interface RoomDao extends CrudDao<Room, Integer> {
    List<Room> findAll(Integer hotelId, Integer numberOnPage, Integer firstRoomOnPage, String sorting, String ordering);

    Integer countAll(Integer hotelId);

}
