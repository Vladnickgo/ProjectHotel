package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;

import java.util.List;

public interface HotelService {
    List<HotelDto> findAll(Integer itemsOnPage, Integer numberOfPage);

    Integer getNumberOfPages(Integer itemsOnPage);

    HotelDto getHotelById(Integer id);

    Integer countAll();

    List<HotelDto> findAll();

    Integer initNumberOfPage(String numberOfPage);

    Integer initItemsOnPage(String itemsOnPage);
}

