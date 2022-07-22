package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;
import com.vladnickgofj.hotel.dao.HotelDao;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.service.HotelService;
import com.vladnickgofj.hotel.service.mapper.Mapper;

import java.util.List;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {

    private final HotelDao hotelRepository;
    private final Mapper<HotelDto, Hotel> hotelMapper;

    public HotelServiceImpl(HotelDao hotelRepository, Mapper<HotelDto, Hotel> hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Override
    public List<HotelDto> findAll(PagenableElementsDto paginateHotelDto) {
        return hotelRepository
                .findAll(getFirstRecordOnPage(paginateHotelDto), paginateHotelDto.getItemsOnPage())
                .stream()
                .map(hotelMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    private Integer getFirstRecordOnPage(PagenableElementsDto paginateHotelDto) {
        Integer pages = getNumberOfPages(paginateHotelDto);
        return paginateHotelDto.getItemsOnPage() * ((Math.min(paginateHotelDto.getNumberOfPage(), pages)) - 1);
    }

    @Override
    public Integer getNumberOfPages(PagenableElementsDto paginateHotelDto) {
        Integer size = hotelRepository.countAll();
        return size / paginateHotelDto.getItemsOnPage() + (size % paginateHotelDto.getItemsOnPage() > 0 ? 1 : 0);
    }

    @Override
    public HotelDto getHotelById(Integer id) {
        Hotel byId = hotelRepository.findById(id).orElse(null);
        return hotelMapper.mapEntityToDto(byId);
    }

    @Override
    public Integer countAll(){
        return hotelRepository.countAll();
    }

}