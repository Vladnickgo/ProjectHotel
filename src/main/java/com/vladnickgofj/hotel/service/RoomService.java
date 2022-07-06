package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.PaginateRoomDto;
import com.vladnickgofj.hotel.controller.dto.RoomDto;
import com.vladnickgofj.hotel.controller.dto.RoomDtoResponse;

import java.util.Comparator;
import java.util.List;

public interface RoomService {

    List<RoomDtoResponse> findAll(Integer hotelId, String sorting, String ordering, Comparator<RoomDtoResponse> comparator, PaginateRoomDto paginateRoomDto);

    Integer countAll(Integer hotelId);

    Integer getNumberOfPages(PaginateRoomDto paginateRoomDto, Integer hotelId);
}
