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
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPassword(),
                user.getRole());
    }
}
