package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.entity.*;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomType;

public class UsersOrderMapper implements Mapper<UsersOrderDto, UsersOrder> {
    @Override
    public UsersOrder mapDtoToEntity(UsersOrderDto usersOrderDto) {
        return UsersOrder.newBuilder()
                .id(usersOrderDto.getId())
                .user(User.newBuilder()
                        .id(usersOrderDto.getUserDto().getId())
                        .firstName(usersOrderDto.getUserDto().getFirstName())
                        .lastName(usersOrderDto.getUserDto().getLastName())
                        .email(usersOrderDto.getUserDto().getEmail())
                        .build())
                .hotel(Hotel.newBuilder()
                        .id(usersOrderDto.getHotelDto().getId())
                        .build())
                .dateStart(usersOrderDto.getDateStart())
                .dateEnd(usersOrderDto.getDateEnd())
                .orderDate(usersOrderDto.getOrderDate())
                .numberOfPersons(usersOrderDto.getNumberOfPersons())
                .room(Room.newBuilder()
                        .id(usersOrderDto.getRoomDtoResponse().getId())
                        .roomType(RoomType.newBuilder()
                                .id(usersOrderDto.getRoomDtoResponse().getRoomType().getTypeId())
                                .build())
                        .build())
                .orderStatus(usersOrderDto.getOrderStatus())
                .build();
    }

    @Override
    public UsersOrderDto mapEntityToDto(UsersOrder usersOrder) {
        return UsersOrderDto.newBuilder()
                .id(usersOrder.getId())
                .userDto(UserDto.newBuilder()
                        .id(usersOrder.getUser().getId())
                        .firstName(usersOrder.getUser().getFirstName())
                        .lastName(usersOrder.getUser().getLastName())
                        .email(usersOrder.getUser().getEmail())
                        .build())
                .hotelDto(HotelDto.newBuilder()
                        .id(usersOrder.getHotel().getId())
                        .name(usersOrder.getHotel().getName())
                        .build())
                .dateStart(usersOrder.getDateStart())
                .dateEnd(usersOrder.getDateEnd())
                .orderDate(usersOrder.getOrderDate())
                .numberOfPersons(usersOrder.getNumberOfPersons())
                .roomDtoResponse(RoomDtoResponse.newBuilder()
                        .roomType(com.vladnickgofj.hotel.controller.dto.RoomTypeDto.newBuilder()
                                .typeId(usersOrder.getRoom().getRoomType().getId())
                                .typeName(usersOrder.getRoom().getRoomType().getTypeName())
                                .build())
                        .build())
                .orderStatus(usersOrder.getOrderStatus())
                .build();
    }
}
