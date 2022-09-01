package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.RoomDao;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomDaoImplTest {


    @ParameterizedTest(name = "[{index}{1}]")
    @MethodSource("provideDataForCheckingFindAllRoom")
    void checkRoomDaoMethodFindAll(List<Room> expectedList, String message) {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        RoomDao roomDao = new RoomDaoImpl(connectionPool);
        List<Room> actualList = roomDao.findAll();
        Arrays.sort(new List[]{expectedList});
        Arrays.sort(new List[]{actualList});
        assertEquals(actualList, expectedList, message);
    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideDataForCheckingCountRoom")
    void checkRoomDaoMethodCountAll(int hotelId, int countExpected, String message) {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        RoomDao roomDao = new RoomDaoImpl(connectionPool);
        Integer countActual = roomDao.countAll(hotelId);
        Assertions.assertEquals(countExpected, countActual, message);

    }

    private static Stream<Arguments> provideDataForCheckingFindAllRoom() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                Room.newBuilder()
                                        .id(2)
                                        .roomType(RoomType.newBuilder()
                                                .id(1)
                                                .typeName("Single")
                                                .build())
                                        .numberOfBeds(1)
                                        .price(600)
                                        .hotel(Hotel.newBuilder()
                                                .id(1)
                                                .name("Eleon")
                                                .build())
                                        .build(),
                                Room.newBuilder()
                                        .id(5)
                                        .roomType(RoomType.newBuilder()
                                                .id(1)
                                                .typeName("Single")
                                                .build())
                                        .numberOfBeds(1)
                                        .price(600)
                                        .hotel(Hotel.newBuilder()
                                                .id(2)
                                                .name("Kyiv")
                                                .build())
                                        .build(),
                                Room.newBuilder()
                                        .id(3)
                                        .roomType(RoomType.newBuilder()
                                                .id(2)
                                                .typeName("Double")
                                                .build())
                                        .numberOfBeds(1)
                                        .price(1200)
                                        .hotel(Hotel.newBuilder()
                                                .id(1)
                                                .name("Eleon")
                                                .build())
                                        .build(),
                                Room.newBuilder()
                                        .id(7)
                                        .roomType(RoomType.newBuilder()
                                                .id(5)
                                                .typeName("Queen")
                                                .build())
                                        .numberOfBeds(2)
                                        .price(1200)
                                        .hotel(Hotel.newBuilder()
                                                .id(2)
                                                .name("Kyiv")
                                                .build())
                                        .build(),
                                Room.newBuilder()
                                        .id(4)
                                        .roomType(RoomType.newBuilder()
                                                .id(2)
                                                .typeName("Double")
                                                .build())
                                        .numberOfBeds(2)
                                        .price(1100)
                                        .hotel(Hotel.newBuilder()
                                                .id(1)
                                                .name("Eleon")
                                                .build())
                                        .build(),
                                Room.newBuilder()
                                        .id(6)
                                        .roomType(RoomType.newBuilder()
                                                .id(2)
                                                .typeName("Double")
                                                .build())
                                        .numberOfBeds(2)
                                        .price(1100)
                                        .hotel(Hotel.newBuilder()
                                                .id(2)
                                                .name("Kyiv")
                                                .build())
                                        .build(),
                                Room.newBuilder()
                                        .id(1)
                                        .roomType(RoomType.newBuilder()
                                                .id(1)
                                                .typeName("Single")
                                                .build())
                                        .numberOfBeds(1)
                                        .price(700)
                                        .hotel(Hotel.newBuilder()
                                                .id(1)
                                                .name("Eleon")
                                                .build())
                                        .build()), "Test for findAll method"));
    }

    private static Stream<Arguments> provideDataForCheckingCountRoom() {
        return Stream.of(Arguments.of(1, 4, "Test countAll method for hotelId 1"),
                Arguments.of(2, 3, "Test countAll method for hotelId 2"));
    }

}