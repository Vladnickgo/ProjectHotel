package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.UserDao;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.service.UserService;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.validator.UserValidator;


public class UserServiceImpl implements UserService {

    private final HikariConnectionPool hikariConnectionPool;
    private final UserDao userRepository;
    private final Mapper<UserDto, User> mapper;
    private final UserValidator userValidator;

    public UserServiceImpl(HikariConnectionPool hikariConnectionPool, UserDao userRepository, Mapper<UserDto, User> mapper, UserValidator userValidator) {
        this.hikariConnectionPool = hikariConnectionPool;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userValidator = userValidator;
    }

    @Override
    public UserDto findByEmail(String email) {
        userValidator.validateEmail(email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User with email [" + email + "] not found"));
        return mapper.mapEntityToDto(user);
    }

    @Override
    public void save(UserDto userDto) {
        System.out.println("Hello from UserServiceImpl, method save()");
        userValidator.validate(userDto);
        userRepository.findByEmail(userDto.getEmail()).ifPresent(err -> {
            throw new RuntimeException("Email is not correct");
        });
        User user = mapper.mapDtoToEntity(userDto);
        userRepository.save(user);

    }

    @Override
    public void register(UserDto userDto) {
        userValidator.validate(userDto);
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("A user with this Email is already registered");
        }
    }

}
