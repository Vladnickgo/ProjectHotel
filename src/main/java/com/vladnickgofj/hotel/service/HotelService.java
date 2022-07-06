package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PaginateHotelDto;

import java.util.List;

public interface HotelService {
    List<HotelDto> findAll(PaginateHotelDto paginateHotelDto);

    Integer getNumberOfPages(PaginateHotelDto paginateHotelDto);

    HotelDto getHotelById(Integer id);

    Integer countAll();
}
