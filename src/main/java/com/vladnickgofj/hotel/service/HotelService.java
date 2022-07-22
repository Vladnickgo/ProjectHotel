package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;

import java.util.List;

public interface HotelService {
    List<HotelDto> findAll(PagenableElementsDto paginateHotelDto);

    Integer getNumberOfPages(PagenableElementsDto paginateHotelDto);

    HotelDto getHotelById(Integer id);

    Integer countAll();
}
