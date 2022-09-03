package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.UserDao;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.service.UserService;
import com.vladnickgofj.hotel.service.exception.EntityAlreadyExistException;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import com.vladnickgofj.hotel.service.util.PasswordEncryptionService;
import com.vladnickgofj.hotel.validator.UserValidator;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.*;

public class UserServiceImpl implements UserService {

    private final UserDao userRepository;
    private final Mapper<UserDto, User> mapper;
    private final UserValidator userValidator;
    private final PasswordEncryptionService passwordEncryptionService;
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDao userRepository, Mapper<UserDto, User> mapper, UserValidator userValidator, PasswordEncryptionService passwordEncryptionService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userValidator = userValidator;
        this.passwordEncryptionService = passwordEncryptionService;
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = getUser(email);
        return mapper.mapEntityToDto(user);
    }

    private User getUser(String email) {
        userValidator.validateEmail(email);
        return userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException(String.format(USER_WITH_EMAIL_NOT_FOUND, email)));
    }

    @Override
    public void save(UserDto userDto) {
        userValidator.validate(userDto);
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            String message = String.format(USER_ALREADY_EXIST_ERROR_MESSAGE, userDto.getEmail());
            LOGGER.info(message);
            throw new EntityAlreadyExistException(message);
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
        User user = getUser(email);
        try {
            if (passwordEncryptionService.authenticate(password, user.getPassword(), user.getSalt())) {
                return mapper.mapEntityToDto(user);
            }
            throw new IllegalArgumentException(PASSWORD_ERROR_MESSAGE);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(PASSWORD_ERROR_MESSAGE);
        }
    }

}
