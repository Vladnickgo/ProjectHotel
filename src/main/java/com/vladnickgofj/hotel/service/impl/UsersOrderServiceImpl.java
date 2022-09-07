package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import com.vladnickgofj.hotel.dao.UsersOrderDao;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.entity.UsersOrder;
import com.vladnickgofj.hotel.service.UsersOrderService;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import com.vladnickgofj.hotel.service.util.UsersOrderRequestDtoUtil;
import com.vladnickgofj.hotel.validator.UsersOrderValidator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UsersOrderServiceImpl implements UsersOrderService {
    private final UsersOrderDao usersOrderDao;
    private final Mapper<UsersOrderDto, UsersOrder> userOrderMapper;
    private final Mapper<RoomStatusDto, RoomStatus> roomStatusMapper;
    private final UsersOrderValidator usersOrderValidator;


    public UsersOrderServiceImpl(UsersOrderDao usersOrderDao, Mapper<UsersOrderDto, UsersOrder> mapper, Mapper<RoomStatusDto, RoomStatus> roomStatusMapper, UsersOrderValidator usersOrderValidator) {
        this.usersOrderDao = usersOrderDao;
        this.userOrderMapper = mapper;
        this.roomStatusMapper = roomStatusMapper;
        this.usersOrderValidator = usersOrderValidator;
    }

    @Override
    public void save(UsersOrderDto usersOrderDto) {
        usersOrderValidator.validate(usersOrderDto);
        UsersOrder usersOrder = userOrderMapper.mapDtoToEntity(usersOrderDto);
        usersOrderDao.save(usersOrder);
    }

    @Override
    public List<UsersOrderDto> findAllByParameters(UsersOrderRequestDtoUtil usersOrderRequestDto) {
        Comparator<UsersOrderDto> usersOrderDtoComparator = usersOrderRequestDto.extractedComparator();
        return usersOrderDao.findAllByParameters(usersOrderRequestDto)
                .stream()
                .map(userOrderMapper::mapEntityToDto)
                .sorted(usersOrderDtoComparator)
                .collect(Collectors.toList());
    }

    @Override
    public Integer totalPages(UsersOrderRequestDtoUtil usersOrderRequestDto) {
        Integer countAll = usersOrderDao.countAll(usersOrderRequestDto);
        Integer itemsOnPage = usersOrderRequestDto.getItemsOnPage();
        return (countAll - 1) / itemsOnPage + 1;
    }

    @Override
    public void completeUsersOrder(List<UsersOrderDto> usersOrderDtoList, List<RoomStatusDto> roomStatusDtoListList) {
        List<UsersOrder> usersOrdersList = usersOrderDtoList.stream()
                .map(userOrderMapper::mapDtoToEntity)
                .collect(Collectors.toList());
        List<RoomStatus> roomStatusList = roomStatusDtoListList.stream()
                .map(roomStatusMapper::mapDtoToEntity)
                .collect(Collectors.toList());
        usersOrderDao.completeUsersOrder(usersOrdersList, roomStatusList);
    }

    @Override
    public void updateUsersOrderById(Integer orderId) {
        usersOrderDao.updateUsersOrderById(orderId);
    }

}
