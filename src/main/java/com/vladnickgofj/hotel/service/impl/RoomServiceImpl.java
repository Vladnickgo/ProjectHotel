package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.RoomDto;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.RoomDao;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.service.RoomService;
import com.vladnickgofj.hotel.service.mapper.Mapper;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {

    private final RoomDao roomRepository;
    private final Mapper<RoomDto, Room> mapper;

    public RoomServiceImpl(RoomDao roomRepository, Mapper<RoomDto, Room> mapper) {
        this.roomRepository = roomRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RoomDto> findByHotelId(List<RoomDto> list, Integer hotelId) {
        return findAll()
                .stream()
                .filter(t -> t.getHotelId() == hotelId)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> findByTypeId(List<RoomDto> list, Integer typeId) {
        return findAll()
                .stream()
                .filter(t -> t.getRoomTypeId() == typeId)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> findByStatusId(List<RoomDto> list, Integer statusId) {
        return findAll()
                .stream()
                .filter(t -> t.getRoomStatusId() == statusId)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> findByNumberOfBeds(List<RoomDto> list, Integer numberOfBeds) {
        return findAll()
                .stream()
                .filter(t -> t.getNumberOfBeds() == numberOfBeds)
                .collect(Collectors.toList());
    }


    @Override
    public List<RoomDto> findAll() {
        return roomRepository
                .findAll()
                .stream()
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    private List<RoomDto> orderByParam(List<RoomDto> roomDtoList, Function<UserDto,String> param){
        return null;
    }
    private List<RoomDto> findByParam(){
        return null;
    }


}
