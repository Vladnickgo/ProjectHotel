package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.service.util.PasswordEncryptionService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.PASSWORD_ERROR_MESSAGE;

public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public User mapDtoToEntity(UserDto userDto) {
        PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService();
        String salt;
        String encryptedPassword;
        try {
            salt = passwordEncryptionService.generateSalt();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(PASSWORD_ERROR_MESSAGE);
        }
        try {
            encryptedPassword = passwordEncryptionService.getEncryptedPassword(userDto.getPassword(), salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(PASSWORD_ERROR_MESSAGE);
        }
        return User.newBuilder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .salt(salt)
                .password(encryptedPassword)
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
