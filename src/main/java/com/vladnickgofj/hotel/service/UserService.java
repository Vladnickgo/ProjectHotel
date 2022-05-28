package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.UserDto;

public interface UserService {

    UserDto findByEmail(String email);

    void save(UserDto userDto);

    UserDto login(String email, String password);
}
