package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;

import java.util.Comparator;
import java.util.List;

public interface RoomStatusService {
    RoomStatusDto getRoomStatusById(Integer id);

    List<RoomStatusDto> findAll(Integer hotelId, RoomStatusDto roomStatusDto, String roomStatusQuerySubstitute, PagenableElementsDto pagenableElementsDto, String sorting, String ordering, Comparator<RoomStatusDto> comparator);

    Integer getNumberOfPages(PagenableElementsDto pagenableElementsDto, Integer id, String roomStatusQuerySubstitute, RoomStatusDto roomStatusDto);
}
