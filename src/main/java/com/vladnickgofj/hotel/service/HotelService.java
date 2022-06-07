package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PaginateHotelDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;

import java.util.List;

public interface HotelService {
    List<HotelDto> findAll(PaginateHotelDto paginateHotelDto);
    Integer getPages(PaginateHotelDto paginateHotelDto);
}
