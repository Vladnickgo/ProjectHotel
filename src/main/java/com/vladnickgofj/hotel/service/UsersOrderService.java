package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderRequestDto;
import com.vladnickgofj.hotel.dao.entity.UsersOrder;
import com.vladnickgofj.hotel.service.util.UsersOrderRequestDtoUtil;

import java.util.List;

public interface UsersOrderService {
    void save(UsersOrderDto usersOrderDto);

    List<UsersOrderDto> findAllByParameters(UsersOrderRequestDtoUtil usersOrderRequestDto);

    Integer totalPages(UsersOrderRequestDtoUtil usersOrderRequestDto);

    Integer totalPages(UsersOrderRequestDtoUtil usersOrderRequestDto,Integer userId);

    void completeUsersOrder(List<UsersOrderDto> usersOrderDtoList, List<RoomStatusDto> roomStatusIdList);

    void updateUsersOrderById(Integer valueOf);

    List<UsersOrderDto> findUsersOrdersByParameters(UsersOrderRequestDtoUtil usersOrderRequestDto, Integer userId);

}
