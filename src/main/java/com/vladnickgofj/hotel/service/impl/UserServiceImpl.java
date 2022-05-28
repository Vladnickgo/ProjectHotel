package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.UserDao;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.service.UserService;
import com.vladnickgofj.hotel.service.exception.EntityAlreadyExistException;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import com.vladnickgofj.hotel.validator.UserValidator;
import org.apache.log4j.Logger;

import java.util.Objects;

import static com.vladnickgofj.hotel.validator.Patterns.REGEX_FOR_EMAIL;
import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.*;

public class UserServiceImpl implements UserService {

    private final UserDao userRepository;
    private final Mapper<UserDto, User> mapper;
    private final UserValidator userValidator;
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDao userRepository, Mapper<UserDto, User> mapper, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userValidator = userValidator;
    }

    @Override
    public UserDto findByEmail(String email) {
        userValidator.validateEmail(email);
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException(String.format(USER_WITH_EMAIL_NOT_FOUND, email)));
        return mapper.mapEntityToDto(user);
    }

    @Override
    public void save(UserDto userDto) {
        userValidator.validate(userDto);
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            LOGGER.info(USER_ALREADY_EXIST_ERROR_MESSAGE);
            throw new EntityAlreadyExistException(String.format(USER_ALREADY_EXIST_ERROR_MESSAGE, userDto.getEmail()));
        }
        String password = userDto.getPassword();
        String confirmationPassword = userDto.getConfirmationPassword();
        if (!Objects.equals(password, confirmationPassword) || Objects.equals(password, "")) {
            LOGGER.info(CONFIRMATION_PASSWORD_ERROR_MESSAGE);
            throw new IllegalArgumentException(CONFIRMATION_PASSWORD_ERROR_MESSAGE);
        }
        User user = mapper.mapDtoToEntity(userDto);
        userRepository.save(user);
    }


    @Override
    public UserDto login(String email, String password) {
        UserDto user = findByEmail(email);
        if (emailValidation(email)) {
            LOGGER.info(EMAIL_ERROR_MESSAGE);
            throw new IllegalArgumentException(EMAIL_ERROR_MESSAGE);
        }
        if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
            return UserDto.newBuilder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();
        } else {
            throw new IllegalArgumentException(PASSWORD_ERROR_MESSAGE);
        }
    }

    private boolean emailValidation(String email) {
        return !email.matches(REGEX_FOR_EMAIL);
    }
}
