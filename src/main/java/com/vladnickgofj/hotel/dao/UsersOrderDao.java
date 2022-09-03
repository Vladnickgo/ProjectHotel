package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderRequestDto;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.entity.UsersOrder;

import java.util.List;

public interface UsersOrderDao extends CrudDao<UsersOrder, Integer> {


    List<UsersOrder> findAllByParameters(UsersOrderRequestDto usersOrderRequestDto);

    Integer countAll(UsersOrderRequestDto usersOrderRequestDto);

    void completeUsersOrder(List<UsersOrder> usersOrdersList, List<RoomStatus> roomStatusIdList);

    void updateUsersOrderById(Integer orderId);
}
