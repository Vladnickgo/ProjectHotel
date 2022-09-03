package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;
import com.vladnickgofj.hotel.controller.dto.RoomDtoResponse;

import java.util.Comparator;
import java.util.List;

public interface RoomService {

    List<RoomDtoResponse> findAll(Integer hotelId, String sorting, String ordering, Comparator<RoomDtoResponse> comparator, PagenableElementsDto pagenableElementsDto);

    Integer countAll(Integer hotelId);

    Integer getNumberOfPages(PagenableElementsDto pagenableElementsDto, Integer hotelId);
}
