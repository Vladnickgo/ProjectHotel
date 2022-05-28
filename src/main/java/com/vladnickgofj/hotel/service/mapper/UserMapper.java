package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.controller.dto.UserDto;

public class UserMapper implements Mapper<UserDto, User> {

    @Override
    public User mapDtoToEntity(UserDto userDto) {
        return User.newBuilder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

    @Override
    public UserDto mapEntityToDto(User user) {
        return UserDto.newBuilder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }
}
