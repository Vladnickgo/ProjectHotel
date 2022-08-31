package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Role;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

class UserDaoImplTest {
    private final HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute("DELETE FROM testdb.users; ");
            statement.execute("INSERT INTO users (first_name, last_name, email, password, salt, role_id) VALUES ('Andrey', 'Mastykov', 'andrey@mail.com', '0a233ec67ca2b482e6d3e3c62941135d1b660199', 'e8afd5d44e2be783', 2), " +
                    "('Sergey', 'Galushtenko', 'sergey@mail.com', '922e22af7f9762d93d314d2b9deea821f8666581', '420f28d49532f09a', 2) ");

        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute("DELETE FROM testdb.users; ");

        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideEmailForUserDao")
    void findByEmail(String email, User expected, String message) {

        UserDaoImpl userDao = new UserDaoImpl(connectionPool);
        User user = userDao.findByEmail(email).orElse(null);
        User actual = User.newBuilder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .salt(user.getSalt())
                .role(user.getRole())
                .build();

        Assertions.assertEquals(actual, expected);

    }

    private static Stream<Arguments> provideEmailForUserDao() {
        return Stream.of(
                Arguments.of("andrey@mail.com",
                        User.newBuilder()
                                .firstName("Andrey")
                                .lastName("Mastykov")
                                .email("andrey@mail.com")
                                .password("0a233ec67ca2b482e6d3e3c62941135d1b660199")
                                .salt("e8afd5d44e2be783")
                                .role(Role.USER)
                                .build(),
                        "Check for email: andrey@mail.com"),
                Arguments.of("sergey@mail.com",
                        User.newBuilder()
                                .firstName("Sergey")
                                .lastName("Galushtenko")
                                .email("sergey@mail.com")
                                .password("922e22af7f9762d93d314d2b9deea821f8666581")
                                .salt("420f28d49532f09a")
                                .role(Role.USER)
                                .build(),
                        "Check for email: sergey@mail.com")

        );
    }
}