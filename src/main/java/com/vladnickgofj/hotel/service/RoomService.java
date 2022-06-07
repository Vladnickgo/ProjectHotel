package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.RoomDto;

import java.util.List;

public interface RoomService {
    List<RoomDto> findByHotelId(List<RoomDto> dtoList,Integer hotelId);
    List<RoomDto> findByTypeId(List<RoomDto> dtoList,Integer id);
    List<RoomDto> findByStatusId(List<RoomDto> dtoList,Integer statusId);
    List<RoomDto> findByNumberOfBeds(List<RoomDto> dtoList,Integer numberOfBeds);
    List<RoomDto> findAll();

}
