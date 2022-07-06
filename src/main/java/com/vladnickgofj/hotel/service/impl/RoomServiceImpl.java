package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.PaginateRoomDto;
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
    public List<RoomDtoResponse> findAll(Integer hotelId, String sorting, String ordering, Comparator<RoomDtoResponse> comparator, PaginateRoomDto paginateRoomDto) {
        return roomRepository
                .findAll(hotelId, getFirstRecordOnPage(paginateRoomDto, hotelId), paginateRoomDto.getRoomsOnPage(), sorting, ordering)
                .stream()
                .map(mapper::mapEntityToDto)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public Integer countAll(Integer hotelId) {
        return roomRepository.countAll(hotelId);
    }


    private Integer getFirstRecordOnPage(PaginateRoomDto paginateRoomDto, Integer hotelId) {
        Integer pages = getNumberOfPages(paginateRoomDto, hotelId);
        return paginateRoomDto.getRoomsOnPage() * ((Math.min(paginateRoomDto.getNumberOfPage(), pages)) - 1);
    }

    @Override
    public Integer getNumberOfPages(PaginateRoomDto paginateRoomDto, Integer hotelId) {
        Integer size = countAll(hotelId);
        return size / paginateRoomDto.getRoomsOnPage() + (size % paginateRoomDto.getRoomsOnPage() > 0 ? 1 : 0);
    }

}