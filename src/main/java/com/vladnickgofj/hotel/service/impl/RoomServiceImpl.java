package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;
import com.vladnickgofj.hotel.controller.dto.RoomDtoResponse;
import com.vladnickgofj.hotel.dao.RoomDao;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.service.RoomService;
import com.vladnickgofj.hotel.service.mapper.Mapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {

    private final RoomDao roomRepository;
    private final Mapper<RoomDtoResponse, Room> mapper;

    public RoomServiceImpl(RoomDao roomRepository, Mapper<RoomDtoResponse, Room> mapper) {
        this.roomRepository = roomRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RoomDtoResponse> findAll(Integer hotelId, String sorting, String ordering, Comparator<RoomDtoResponse> comparator, PagenableElementsDto pagenableElementsDto) {
        return roomRepository
                .findAll(hotelId, getFirstRecordOnPage(pagenableElementsDto, hotelId), pagenableElementsDto.getItemsOnPage(), sorting, ordering)
                .stream()
                .map(mapper::mapEntityToDto)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public Integer countAll(Integer hotelId) {
        return roomRepository.countAll(hotelId);
    }

    private Integer getFirstRecordOnPage(PagenableElementsDto pagenableElementsDto, Integer hotelId) {
        Integer pages = getNumberOfPages(pagenableElementsDto, hotelId);
        return pagenableElementsDto.getItemsOnPage() * ((Math.min(pagenableElementsDto.getNumberOfPage(), pages)) - 1);
    }

    @Override
    public Integer getNumberOfPages(PagenableElementsDto paginableElementsDto, Integer hotelId) {
        Integer size = countAll(hotelId);
        return size / paginableElementsDto.getItemsOnPage() + (size % paginableElementsDto.getItemsOnPage() > 0 ? 1 : 0);
    }

}