package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PaginateHotelDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;

public class HotelMapper implements Mapper<HotelDto, Hotel>{
    @Override
    public Hotel mapDtoToEntity(HotelDto hotelDto) {
        return Hotel.newBuilder()
                .id(hotelDto.getId())
                .name(hotelDto.getName())
                .build();
    }

    @Override
    public HotelDto mapEntityToDto(Hotel hotel) {
        return HotelDto.newBuilder()
                .id(hotel.getId())
                .name(hotel.getName())
                .build();
    }
}
