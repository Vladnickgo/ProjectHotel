package com.vladnickgofj.hotel.validator;

import com.vladnickgofj.hotel.controller.dto.UserDto;
import com.vladnickgofj.hotel.dao.entity.Role;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserValidatorTest {
    private final UserValidator userValidator = new UserValidator();

    @ParameterizedTest
    @MethodSource("provideUserDtoForValidate")
    public void userValidateTest(UserDto userDto, String message) throws IllegalArgumentException {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> userValidator.validate(userDto));
        assertEquals(message, throwable.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123@123", "абвгд@жзкю.тлдж", "test@test#test.com", "test@test,com", "email.com"})
    @NullAndEmptySource
    public void checkThrowableEmailValidateException(String email) throws IllegalArgumentException {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> userValidator.validateEmail(email));
        assertEquals("Email is not valid", throwable.getMessage());

    }

    private static Stream<Arguments> provideUserDtoForValidate() {
        return Stream.of(
                Arguments.of(UserDto.newBuilder()
                        .id(1)
                        .firstName("john")
                        .lastName("Doe")
                        .email("test@gmail.com")
                        .password("somePassword")
                        .role(Role.USER)
                        .confirmationPassword("somePassword")
                        .build(), "First name is not valid"),
                Arguments.of(UserDto.newBuilder()
                        .id(1)
                        .firstName("John")
                        .lastName("doe")
                        .email("test@gmail.com")
                        .role(Role.USER)
                        .password("somePassword")
                        .confirmationPassword("somePassword")
                        .build(), "Last name is not valid"),
                Arguments.of(UserDto.newBuilder()
                        .id(1)
                        .firstName("John")
                        .lastName("Doe")
                        .email("test@ gmail.com")
                        .role(Role.USER)
                        .password("somePassword")
                        .confirmationPassword("somePassword")
                        .build(), "Email is not valid"),
                Arguments.of(UserDto.newBuilder()
                        .id(1)
                        .firstName("John")
                        .lastName("Doe")
                        .email("test@gmail.com")
                        .role(Role.USER)
                        .password("some Password")
                        .confirmationPassword("somePassword")
                        .build(), "Password is not valid"),
                Arguments.of(UserDto.newBuilder()
                        .id(1)
                        .firstName("John")
                        .lastName("Doe")
                        .email("test@gmail.com")
                        .role(Role.USER)
                        .password("somePassword")
                        .confirmationPassword("some Password")
                        .build(), "ConfirmationPassword is not valid"),
                Arguments.of(UserDto.newBuilder()
                        .id(1)
                        .firstName("John")
                        .lastName("Doe")
                        .email("")
                        .role(Role.USER)
                        .password("somePassword")
                        .confirmationPassword("somePassword")
                        .build(), "Email is not valid")

        );
    }
}