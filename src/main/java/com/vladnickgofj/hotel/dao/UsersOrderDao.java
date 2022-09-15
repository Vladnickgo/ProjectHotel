package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderRequestDto;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.entity.UsersOrder;
import com.vladnickgofj.hotel.service.util.UsersOrderRequestDtoUtil;

import java.util.List;

public interface UsersOrderDao extends CrudDao<UsersOrder, Integer> {


    List<UsersOrder> findAllByParameters(UsersOrderRequestDtoUtil usersOrderRequestDto);

    Integer countAll(UsersOrderRequestDtoUtil usersOrderRequestDto);

    void completeUsersOrder(List<UsersOrder> usersOrdersList, List<RoomStatus> roomStatusIdList);

    void updateUsersOrderById(Integer orderId);

    List<UsersOrder> findUsersOrdersByParameters(UsersOrderRequestDtoUtil usersOrderRequestDto, Integer userId);

    Integer countUsersOrdersByParameters(UsersOrderRequestDtoUtil usersOrderRequestDto,Integer userId);
}
