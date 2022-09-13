package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.RoomStatusDao;
import com.vladnickgofj.hotel.dao.entity.*;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;
import com.vladnickgofj.hotel.service.util.RoomStatusDtoRequestUtil;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomStatusDaoImplTest {
    private static final Logger LOGGER = Logger.getLogger(RoomStatusDaoImplTest.class);
    private final HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
    @BeforeEach
    void setUp() {
        LOGGER.info("setup");
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {

            try {
                connection.setAutoCommit(false);
                statement.execute("INSERT INTO testdb.bookings(booking_id, check_in, check_out, room_id, book_time, booking_status_id, user_id) VALUES " +
                        "(1,'2022-09-05','2022-09-10',5,'2022-09-03',1, 1), " +
                        "(2,'2022-09-07','2022-09-12',6,'2022-09-02',1, 1), " +
                        "(3,'2022-01-08','2022-01-12',4,'2022-01-02',2, 2), " +
                        "(4,'2022-09-09','2022-09-14',3,'2022-09-03',1, 2), " +
                        "(5,'2022-09-10','2022-09-14',3,'2022-09-05',2, 3), " +
                        "(6,'2022-01-11','2022-01-15',2,'2022-01-06',3, 3); ");
                statement.execute("UPDATE room_status SET date_end='2022-09-05' WHERE status_id = 5 ");
                statement.execute("UPDATE room_status SET date_end='2022-09-07' WHERE status_id = 6 ");
                statement.execute("UPDATE room_status SET date_end='2022-09-08' WHERE status_id = 4 ");
                statement.execute("UPDATE room_status SET date_end='2022-09-09' WHERE status_id = 3 ");
                statement.execute("INSERT INTO " +
                        "room_status (date_start, date_end, room_id, status_statement_id) " +
                        "VALUES ('2022-09-05','2022-09-10',5, 2), ('2022-09-10', '2022-11-01', 5, 1), " +
                        "('2022-09-07','2022-09-12',6, 2), ('2022-09-12', '2022-11-01', 6, 1), " +
                        "('2022-01-08','2022-01-12',4, 2), ('2022-09-12', '2022-11-01', 4, 1), " +
                        "('2022-09-09','2022-09-14',3, 2), ('2022-09-14', '2022-11-01', 3, 1); ");
                connection.commit();
                LOGGER.info("Transaction completed");
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.info("Rollback of transaction");
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
//        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        LOGGER.info("tearDown()");
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute("DELETE FROM testdb.bookings WHERE testdb.bookings.booking_id>0; ");
            try {
                connection.setAutoCommit(false);
                statement.execute("DELETE FROM testdb.bookings WHERE booking_id>1;");
                statement.execute("UPDATE room_status SET date_end='2022-11-01' WHERE status_id = 5 ");
                statement.execute("UPDATE room_status SET date_end='2022-11-01' WHERE status_id = 6 ");
                statement.execute("UPDATE room_status SET date_end='2022-11-01' WHERE status_id = 4 ");
                statement.execute("UPDATE room_status SET date_end='2022-11-01' WHERE status_id = 3 ");
                statement.execute("DELETE FROM room_status WHERE date_start='2022-09-05' AND date_end='2022-09-10' AND room_id=5 ");
                statement.execute("DELETE FROM room_status WHERE date_start='2022-09-10' AND date_end='2022-11-01' AND room_id=5 ");
                statement.execute("DELETE FROM room_status WHERE date_start='2022-09-07' AND date_end='2022-09-12' AND room_id=6 ");
                statement.execute("DELETE FROM room_status WHERE date_start='2022-09-12' AND date_end='2022-11-01' AND room_id=6 ");
                statement.execute("DELETE FROM room_status WHERE date_start='2022-01-08' AND date_end='2022-01-12' AND room_id=4 ");
                statement.execute("DELETE FROM room_status WHERE date_start='2022-09-12' AND date_end='2022-11-01' AND room_id=4 ");
                statement.execute("DELETE FROM room_status WHERE date_start='2022-09-09' AND date_end='2022-09-14' AND room_id=3 ");
                statement.execute("DELETE FROM room_status WHERE date_start='2022-09-14' AND date_end='2022-11-01' AND room_id=3 ");
                connection.commit();
                LOGGER.info("Transaction completed");
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.info("Rollback of transaction");
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @ParameterizedTest(name = "[{index}{3}]")
    @MethodSource("provideDataForFindAllMethod")
    void testFindAllMethodWithParameters(RoomStatusDtoRequestUtil roomStatusDtoRequestUtil, Integer numberOfFirstRecord, List<RoomStatus> expected, String message) {
//        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        RoomStatusDao roomStatusDao = new RoomStatusDaoImpl(connectionPool);
        List<RoomStatus> actual = roomStatusDao.findAll(roomStatusDtoRequestUtil, numberOfFirstRecord);
        assertEquals(expected, actual, message);


    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideDataForCountAllMethod")
    void testCountAllWithNotNullParameters(RoomStatusDtoRequestUtil roomStatusDtoRequestUtil, Integer expectedNumber, String message) {
//        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        RoomStatusDao roomStatusDao = new RoomStatusDaoImpl(connectionPool);
        Integer actualNumber = roomStatusDao.countAll(roomStatusDtoRequestUtil);
        assertEquals(expectedNumber, actualNumber, message);
    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideDataForFindAllFreeByParametersMethod")
    void findAllFreeByParameters(UsersOrderDto usersOrderDto, List<RoomStatus> expectedList, String message) {
//        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        RoomStatusDao roomStatusDao = new RoomStatusDaoImpl(connectionPool);
        List<RoomStatus> actualList = roomStatusDao.findAllFreeByParameters(usersOrderDto);
        assertEquals(expectedList, actualList, message);
    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideDataForFindFreeByRoomIdAndDateStartMethod")
    void findFreeByRoomIdAndDateStart(BookingDto bookingDto, LocalDate expectedDate, String message) {
//        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        RoomStatusDao roomStatusDao = new RoomStatusDaoImpl(connectionPool);
        LocalDate actualDate = roomStatusDao.findFreeByRoomIdAndDateStart(bookingDto);
        assertEquals(expectedDate, actualDate, message);
    }

    @NotNull
    private static Stream<Arguments> provideDataForFindAllMethod() {

        List<RoomStatus> expectedTest1 = new ArrayList<>(
                List.of(
                        RoomStatus.newBuilder()
                                .id(7)
                                .dateStart(LocalDate.parse("2022-08-01"))
                                .dateEnd(LocalDate.parse("2022-11-01"))
                                .room(Room.newBuilder()
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
                                        .build())
                                .statusStatement(StatusStatement.newBuilder()
                                        .statusStatementId(1)
                                        .statusStatementName("free")
                                        .build())
                                .build()));

        List<RoomStatus> expectedTest2 = new ArrayList<>(
                List.of(
                        RoomStatus.newBuilder()
                                .id(2)
                                .dateStart(LocalDate.parse("2022-08-01"))
                                .dateEnd(LocalDate.parse("2022-11-01"))
                                .room(Room.newBuilder()
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
                                        .build())
                                .statusStatement(StatusStatement.newBuilder()
                                        .statusStatementId(1)
                                        .statusStatementName("free")
                                        .build())
                                .build(),
                        RoomStatus.newBuilder()
                                .id(1)
                                .dateStart(LocalDate.parse("2022-08-01"))
                                .dateEnd(LocalDate.parse("2022-11-01"))
                                .room(Room.newBuilder()
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
                                        .build())
                                .statusStatement(StatusStatement.newBuilder()
                                        .statusStatementId(1)
                                        .statusStatementName("free")
                                        .build())
                                .build()));

        return Stream.of(Arguments.of(new RoomStatusDtoRequestUtil(RoomStatusDtoRequest.newBuilder()
                                .hotelId("2")
                                .signInStr("2022-09-05")
                                .signOutStr("2022-09-10")
                                .statusFree("free")
                                .build()),
                        0, expectedTest1, "Test for findAll for hotelId=2"
                ),
                Arguments.of(new RoomStatusDtoRequestUtil(RoomStatusDtoRequest.newBuilder()
                                .hotelId("1")
                                .signInStr("2022-09-05")
                                .signOutStr("2022-09-10")
                                .statusFree("free")
                                .build()),
                        0, expectedTest2, "Test for findAll for hotelId=1")
        );
    }

    @NotNull
    private static Stream<Arguments> provideDataForCountAllMethod() {
        return Stream.of(Arguments.of(new RoomStatusDtoRequestUtil(RoomStatusDtoRequest.newBuilder()
                                .hotelId("1")
                                .signInStr("2022-09-05")
                                .signOutStr("2022-09-10")
                                .statusFree("free")
                                .build()),
                        2, "Test for countAll for hotelId=1, roomStatus=\"free\" "),
                Arguments.of(new RoomStatusDtoRequestUtil(RoomStatusDtoRequest.newBuilder()
                                .hotelId("1")
                                .signInStr("2022-09-05")
                                .signOutStr("2022-09-10")
                                .statusFree("booked")
                                .build()),
                        0, "Test for countAll for hotelId=1, roomStatus=\"booked\" ")
        );
    }

    @NotNull
    private static Stream<Arguments> provideDataForFindAllFreeByParametersMethod() {
        return Stream.of(Arguments.of(
                UsersOrderDto.newBuilder()
                        .hotelDto(HotelDto.newBuilder()
                                .id(2)
                                .build())
                        .dateStart(LocalDate.parse("2022-09-08"))
                        .dateEnd(LocalDate.parse("2022-09-09"))
                        .build(),
                new ArrayList<>(List.of(RoomStatus.newBuilder()
                        .id(7)
                        .dateStart(LocalDate.parse("2022-08-01"))
                        .dateEnd(LocalDate.parse("2022-11-01"))
                        .room(Room.newBuilder()
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
                                .build())
                        .statusStatement(StatusStatement.newBuilder()
                                .statusStatementId(1)
                                .statusStatementName("free")
                                .build())
                        .build())),
                "Test for findAllFreeByParameters"));
    }

    private static Stream<Arguments> provideDataForFindFreeByRoomIdAndDateStartMethod() {
        return Stream.of(Arguments.of(BookingDto.newBuilder()
                        .room(RoomDto.newBuilder()
                                .id(5)
                                .build())
                        .checkOut(LocalDate.parse("2022-09-10"))
                        .build(),
                LocalDate.parse("2022-11-01"),
                "Test for findFreeByRoomIdAndDateStart for roomId=1, dateStart='2022-09-10'"
        ));
    }

}