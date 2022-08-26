package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.dao.HotelDao;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.service.HotelService;
import com.vladnickgofj.hotel.service.mapper.Mapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {
    private static final Integer DEFAULT_PAGE_NUMBER = 1;
    private static final Integer DEFAULT_HOTELS_ON_PAGE = 8;

    private final HotelDao hotelRepository;
    private final Mapper<HotelDto, Hotel> hotelMapper;

    public HotelServiceImpl(HotelDao hotelRepository, Mapper<HotelDto, Hotel> hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Override
    public List<HotelDto> findAll(Integer itemsOnPage, Integer numberOfPage) {
        Integer firstRecordOnPage = getFirstRecordOnPage(itemsOnPage, numberOfPage);
        return hotelRepository
                .findAll(firstRecordOnPage, itemsOnPage)
                .stream()
                .map(hotelMapper::mapEntityToDto)
                .sorted(Comparator.comparing(HotelDto::getName))
                .collect(Collectors.toList());
    }

    private Integer getFirstRecordOnPage(Integer itemsOnPage, Integer numberOfPage) {
        Integer pages = getNumberOfPages(itemsOnPage);
        return itemsOnPage * ((Math.min(numberOfPage, pages)) - 1);
    }

    @Override
    public Integer getNumberOfPages(Integer itemsOnPage) {
        Integer size = hotelRepository.countAll();
        return size / itemsOnPage + (size % itemsOnPage > 0 ? 1 : 0);
    }

    @Override
    public HotelDto getHotelById(Integer id) {
        Hotel byId = hotelRepository.findById(id).orElse(null);
        return hotelMapper.mapEntityToDto(byId);
    }

    @Override
    public Integer countAll() {
        return hotelRepository.countAll();
    }

    @Override
    public List<HotelDto> findAll() {
        return hotelRepository.findAll()
                .stream()
                .map(hotelMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer initNumberOfPage(String numberOfPage) {
        Integer intValue;
        try {
            intValue = Integer.parseInt(numberOfPage);
        } catch (NumberFormatException e) {
            intValue = DEFAULT_PAGE_NUMBER;
        }
        return intValue;
    }

    @Override
    public Integer initItemsOnPage(String itemsOnPage) {
        Integer intValue;
        try {
            intValue = Integer.parseInt(itemsOnPage);
        } catch (NumberFormatException e) {
            intValue = DEFAULT_HOTELS_ON_PAGE;
        }
        return intValue;
    }
}