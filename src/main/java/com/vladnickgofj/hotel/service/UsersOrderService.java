package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderRequestDto;

import java.util.List;

public interface UsersOrderService {
    void save(UsersOrderDto usersOrderDto);

    List<UsersOrderDto> findAllByParameters(UsersOrderRequestDto usersOrderRequestDto);

    Integer totalPages(UsersOrderRequestDto usersOrderRequestDto);

    void completeUsersOrder(List<UsersOrderDto> usersOrderDtoList, List<RoomStatusDto> roomStatusIdList);

    void updateUsersOrderById(Integer valueOf);
}
