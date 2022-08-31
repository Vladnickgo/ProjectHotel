package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.RoomDao;
import com.vladnickgofj.hotel.dao.entity.Room;
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
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoomDaoImplTest {

    @BeforeEach
    void setUp() {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
    }

//    @ParameterizedTest(name = "[{index}{2}]")
//    @MethodSource("provideDataForCheckingFindAllRoom")
    @Test
    void checkRoomDaoMethodFindAll() {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        RoomDao roomDao = new RoomDaoImpl(connectionPool);
        List<Room> all = roomDao.findAll();
        System.out.println(all);
    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideDataForCheckingCountRoom")
    void checkRoomDaoMethodCountAll(int hotelId, int countExpected, String message) {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        RoomDao roomDao = new RoomDaoImpl(connectionPool);
        Integer countActual = roomDao.countAll(hotelId);
        Assertions.assertEquals(countExpected, countActual);

    }

    private static Stream<Arguments> provideDataForCheckingCountRoom() {
        return Stream.of(Arguments.of(1, 15, "Test RoomDao countAll() for hotelId=1"),
                Arguments.of(2, 14, "Test RoomDao countAll() for hotelId=2")
        );
    }


}