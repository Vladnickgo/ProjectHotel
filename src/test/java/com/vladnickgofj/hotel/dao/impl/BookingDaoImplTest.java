package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.BookingRequestDto;
import com.vladnickgofj.hotel.dao.BookingDao;
import com.vladnickgofj.hotel.dao.entity.*;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingDaoImplTest {
    private static final Logger LOGGER = Logger.getLogger(BookingDaoImpl.class);

    @BeforeAll
    public static void setUp() {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
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

    @AfterAll
    public static void tearDown() {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
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

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideDataForCheckFindBookingByParameters")
    void checkFindBookingByParametersMethod(BookingDto bookingDto, Booking expected, String message) {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        BookingDao bookingDao = new BookingDaoImpl(connectionPool);
        Booking bookingByParameters = bookingDao.findBookingByParameters(bookingDto);
        Assertions.assertEquals(expected, bookingByParameters, message);
    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideDataForCheckCountAllMethod")
    void checkCountAllMethod(BookingRequestDto bookingRequestDto, Integer expectedNumberOfBookings, String message) {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        BookingDao bookingDao = new BookingDaoImpl(connectionPool);
        Integer actualNumberOfBookings = bookingDao.countAll(bookingRequestDto);
        assertEquals(expectedNumberOfBookings, actualNumberOfBookings, message);
    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideListForCheckCountAllMethod")
    void checkBookingDaoFindAllMethod(List<Booking> expectedBookingList, String message) {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        BookingDao bookingDao = new BookingDaoImpl(connectionPool);
        List<Booking> actualBookingList = bookingDao.findAll();
        boolean isActualListContainsExpected = actualBookingList.containsAll(expectedBookingList);
        boolean isExpectedListContainsActual = expectedBookingList.containsAll(actualBookingList);
        assertTrue(isActualListContainsExpected && isExpectedListContainsActual, message);
    }

    @ParameterizedTest(name = "[{index}{3}]")
    @MethodSource("provideListForCheckFindBookingsByUserIdAndParametersMethod")
    void findBookingsByUserIdAndParameters(BookingRequestDto bookingRequestDto, Integer firstRecordOnPage, List<Booking> expectedBookingList, String message) {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        BookingDao bookingDao = new BookingDaoImpl(connectionPool);
        List<Booking> actualBookingList = bookingDao.findBookingsByUserIdAndParameters(bookingRequestDto, firstRecordOnPage)
                .stream()
                .sorted(Comparator.comparing(t -> t.getRoom().getPrice(), Comparator.naturalOrder()))
                .collect(Collectors.toList());
        assertEquals(expectedBookingList, actualBookingList, message);

    }

    @ParameterizedTest(name = "[{index}{3}]")
    @MethodSource("provideDataForCheckCancelBookingById")
    void testForCancelBookingById(BookingDto bookingDto, LocalDate dateEnd, Integer expectedStatus, String message) {
        HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");
        BookingDao bookingDao = new BookingDaoImpl(connectionPool);
        bookingDao.cancelBookingById(bookingDto, dateEnd);
        Booking booking = bookingDao.findById(1).orElseThrow(()->new IllegalArgumentException(""));
        Integer actualStatus = booking.getBookingStatus().getId();
        assertEquals(expectedStatus, actualStatus, message);
    }

    private static Stream<Arguments> provideDataForCheckFindBookingByParameters() {
        return Stream.of(Arguments.of(BookingDto.newBuilder()
                        .room(Room.newBuilder()
                                .id(5)
                                .build())
                        .checkIn(LocalDate.parse("2022-09-05"))
                        .checkOut(LocalDate.parse("2022-09-10"))
                        .bookTime(LocalDate.parse("2022-09-03"))
                        .userId(1)
                        .build(),
                Booking.newBuilder()
                        .id(1)
                        .checkIn(LocalDate.parse("2022-09-05"))
                        .checkOut(LocalDate.parse("2022-09-10"))
                        .room(Room.newBuilder()
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
                                .build())
                        .night(5)
                        .bookTime(LocalDate.parse("2022-09-03"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(1)
                                .name("not paid")
                                .build())
                        .user(User.newBuilder()
                                .id(1)
                                .firstName("Ivan")
                                .lastName("Bunin")
                                .email("admin@mail.com")
                                .password("fd06dd38d1fd964b21fbfa73c207cda337c45c3a")
                                .salt("7f1195dbf9222e9a")
                                .role(Role.ADMIN)
                                .build())
                        .build(),
                "Test for findBookingsByParameters method")
        );
    }

    private static Stream<Arguments> provideListForCheckCountAllMethod() {
        List<Booking> bookingList = new ArrayList<>(List.of(
                Booking.newBuilder()
                        .id(1)
                        .checkIn(LocalDate.parse("2022-09-05"))
                        .checkOut(LocalDate.parse("2022-09-10"))
                        .room(Room.newBuilder()
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
                                .build())
                        .night(5)
                        .bookTime(LocalDate.parse("2022-09-03"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(1)
                                .name("not paid")
                                .build())
                        .user(User.newBuilder()
                                .id(1)
                                .firstName("Ivan")
                                .lastName("Bunin")
                                .email("admin@mail.com")
                                .password("fd06dd38d1fd964b21fbfa73c207cda337c45c3a")
                                .salt("7f1195dbf9222e9a")
                                .role(Role.ADMIN)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(2)
                        .checkIn(LocalDate.parse("2022-09-07"))
                        .checkOut(LocalDate.parse("2022-09-12"))
                        .room(Room.newBuilder()
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
                                .build())
                        .night(5)
                        .bookTime(LocalDate.parse("2022-09-02"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(1)
                                .name("not paid")
                                .build())
                        .user(User.newBuilder()
                                .id(1)
                                .firstName("Ivan")
                                .lastName("Bunin")
                                .email("admin@mail.com")
                                .password("fd06dd38d1fd964b21fbfa73c207cda337c45c3a")
                                .salt("7f1195dbf9222e9a")
                                .role(Role.ADMIN)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(3)
                        .checkIn(LocalDate.parse("2022-01-08"))
                        .checkOut(LocalDate.parse("2022-01-12"))
                        .room(Room.newBuilder()
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
                                .build())
                        .night(4)
                        .bookTime(LocalDate.parse("2022-01-02"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(2)
                                .name("paid")
                                .build())
                        .user(User.newBuilder()
                                .id(2)
                                .firstName("Andrey")
                                .lastName("Mastykov")
                                .email("andrey@mail.com")
                                .password("0a233ec67ca2b482e6d3e3c62941135d1b660199")
                                .salt("e8afd5d44e2be783")
                                .role(Role.USER)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(4)
                        .checkIn(LocalDate.parse("2022-09-09"))
                        .checkOut(LocalDate.parse("2022-09-14"))
                        .room(Room.newBuilder()
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
                                .build())
                        .night(5)
                        .bookTime(LocalDate.parse("2022-09-03"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(1)
                                .name("not paid")
                                .build())
                        .user(User.newBuilder()
                                .id(2)
                                .firstName("Andrey")
                                .lastName("Mastykov")
                                .email("andrey@mail.com")
                                .password("0a233ec67ca2b482e6d3e3c62941135d1b660199")
                                .salt("e8afd5d44e2be783")
                                .role(Role.USER)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(5)
                        .checkIn(LocalDate.parse("2022-09-10"))
                        .checkOut(LocalDate.parse("2022-09-14"))
                        .room(Room.newBuilder()
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
                                .build())
                        .night(4)
                        .bookTime(LocalDate.parse("2022-09-05"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(2)
                                .name("paid")
                                .build())
                        .user(User.newBuilder()
                                .id(3)
                                .firstName("Sergey")
                                .lastName("Galushtenko")
                                .email("sergey@mail.com")
                                .password("922e22af7f9762d93d314d2b9deea821f8666581")
                                .salt("420f28d49532f09a")
                                .role(Role.USER)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(6)
                        .checkIn(LocalDate.parse("2022-01-11"))
                        .checkOut(LocalDate.parse("2022-01-15"))
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
                        .night(4)
                        .bookTime(LocalDate.parse("2022-01-06"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(3)
                                .name("canceled")
                                .build())
                        .user(User.newBuilder()
                                .id(3)
                                .firstName("Sergey")
                                .lastName("Galushtenko")
                                .email("sergey@mail.com")
                                .password("922e22af7f9762d93d314d2b9deea821f8666581")
                                .salt("420f28d49532f09a")
                                .role(Role.USER)
                                .build())
                        .build())
        );
        return Stream.of(Arguments.of(bookingList, "Test for countAllMethod"));
    }

    private static Stream<Arguments> provideDataForCheckCountAllMethod() {
        return Stream.of(Arguments.of(BookingRequestDto.newBuilder()
                        .userId(1)
                        .statusNotPaid("notPaid")
                        .statusCanceled("canceled")
                        .build(), 2, "Count paid and canceled statuses for userId=1"),
                Arguments.of(BookingRequestDto.newBuilder()
                        .userId(1)
                        .statusNotPaid("notPaid")
                        .statusPaid("paid")
                        .statusCanceled("canceled")
                        .build(), 2, "Count paid, not paid and canceled statuses for userId=1"),
                Arguments.of(BookingRequestDto.newBuilder()
                        .userId(2)
                        .statusCanceled("canceled")
                        .build(), 0, "Count canceled statuses for userId=2"),
                Arguments.of(BookingRequestDto.newBuilder()
                        .userId(3)
                        .statusCanceled("canceled")
                        .build(), 1, "Count canceled statuses for userId=3"),
                Arguments.of(BookingRequestDto.newBuilder()
                        .userId(3)
                        .statusPaid("paid")
                        .build(), 1, "Count paid statuses for userId=3"),
                Arguments.of(BookingRequestDto.newBuilder()
                        .userId(4)
                        .statusCanceled("canceled")
                        .build(), 0, "Count canceled statuses for userId=4")

        );
    }

    private static Stream<Arguments> provideListForCheckFindBookingsByUserIdAndParametersMethod() {
        List<Booking> bookingList = new ArrayList<>(List.of(
                Booking.newBuilder()
                        .id(1)
                        .checkIn(LocalDate.parse("2022-09-05"))
                        .checkOut(LocalDate.parse("2022-09-10"))
                        .room(Room.newBuilder()
                                .id(5)
                                .roomType(RoomType.newBuilder()
                                        .id(1).typeName("Single")
                                        .build())
                                .numberOfBeds(1)
                                .price(600)
                                .hotel(Hotel.newBuilder()
                                        .id(2)
                                        .name("Kyiv")
                                        .build())
                                .build())
                        .night(5)
                        .bookTime(LocalDate.parse("2022-09-03"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(1)
                                .name("not paid")
                                .build())
                        .user(User.newBuilder()
                                .id(1)
                                .firstName("Ivan")
                                .lastName("Bunin")
                                .email("admin@mail.com")
                                .password("fd06dd38d1fd964b21fbfa73c207cda337c45c3a")
                                .salt("7f1195dbf9222e9a")
                                .role(Role.ADMIN)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(2)
                        .checkIn(LocalDate.parse("2022-09-07"))
                        .checkOut(LocalDate.parse("2022-09-12"))
                        .room(Room.newBuilder()
                                .id(6)
                                .roomType(RoomType.newBuilder()
                                        .id(2).typeName("Double")
                                        .build())
                                .numberOfBeds(2)
                                .price(1100)
                                .hotel(Hotel.newBuilder()
                                        .id(2)
                                        .name("Kyiv")
                                        .build())
                                .build())
                        .night(5)
                        .bookTime(LocalDate.parse("2022-09-02"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(1)
                                .name("not paid")
                                .build())
                        .user(User.newBuilder()
                                .id(1)
                                .firstName("Ivan")
                                .lastName("Bunin")
                                .email("admin@mail.com")
                                .password("fd06dd38d1fd964b21fbfa73c207cda337c45c3a")
                                .salt("7f1195dbf9222e9a")
                                .role(Role.ADMIN)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(3)
                        .checkIn(LocalDate.parse("2022-01-08"))
                        .checkOut(LocalDate.parse("2022-01-12"))
                        .room(Room.newBuilder()
                                .id(4)
                                .roomType(RoomType.newBuilder()
                                        .id(2).typeName("Double")
                                        .build())
                                .numberOfBeds(2)
                                .price(1100)
                                .hotel(Hotel.newBuilder()
                                        .id(1)
                                        .name("Eleon")
                                        .build())
                                .build())
                        .night(4)
                        .bookTime(LocalDate.parse("2022-01-02"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(2)
                                .name("paid")
                                .build())
                        .user(User.newBuilder()
                                .id(2)
                                .firstName("Andrey")
                                .lastName("Mastykov")
                                .email("andrey@mail.com")
                                .password("0a233ec67ca2b482e6d3e3c62941135d1b660199")
                                .salt("e8afd5d44e2be783")
                                .role(Role.USER)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(4)
                        .checkIn(LocalDate.parse("2022-09-09"))
                        .checkOut(LocalDate.parse("2022-09-14"))
                        .room(Room.newBuilder()
                                .id(3)
                                .roomType(RoomType.newBuilder()
                                        .id(2).typeName("Double")
                                        .build())
                                .numberOfBeds(1)
                                .price(1200)
                                .hotel(Hotel.newBuilder()
                                        .id(1)
                                        .name("Eleon")
                                        .build())
                                .build())
                        .night(5)
                        .bookTime(LocalDate.parse("2022-09-03"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(1)
                                .name("not paid")
                                .build())
                        .user(User.newBuilder()
                                .id(2)
                                .firstName("Andrey")
                                .lastName("Mastykov")
                                .email("andrey@mail.com")
                                .password("0a233ec67ca2b482e6d3e3c62941135d1b660199")
                                .salt("e8afd5d44e2be783")
                                .role(Role.USER)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(5)
                        .checkIn(LocalDate.parse("2022-09-10"))
                        .checkOut(LocalDate.parse("2022-09-14"))
                        .room(Room.newBuilder()
                                .id(3)
                                .roomType(RoomType.newBuilder()
                                        .id(2).typeName("Double")
                                        .build())
                                .numberOfBeds(1)
                                .price(1200)
                                .hotel(Hotel.newBuilder()
                                        .id(1)
                                        .name("Eleon")
                                        .build())
                                .build())
                        .night(4)
                        .bookTime(LocalDate.parse("2022-09-05"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(2)
                                .name("paid")
                                .build())
                        .user(User.newBuilder()
                                .id(3)
                                .firstName("Sergey")
                                .lastName("Galushtenko")
                                .email("sergey@mail.com")
                                .password("922e22af7f9762d93d314d2b9deea821f8666581")
                                .salt("420f28d49532f09a")
                                .role(Role.USER)
                                .build())
                        .build(),
                Booking.newBuilder()
                        .id(6)
                        .checkIn(LocalDate.parse("2022-01-11"))
                        .checkOut(LocalDate.parse("2022-01-15"))
                        .room(Room.newBuilder()
                                .id(2)
                                .roomType(RoomType.newBuilder()
                                        .id(1).typeName("Single")
                                        .build())
                                .numberOfBeds(1)
                                .price(600)
                                .hotel(Hotel.newBuilder()
                                        .id(1)
                                        .name("Eleon")
                                        .build())
                                .build())
                        .night(4)
                        .bookTime(LocalDate.parse("2022-01-06"))
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(3)
                                .name("canceled")
                                .build())
                        .user(User.newBuilder()
                                .id(3)
                                .firstName("Sergey")
                                .lastName("Galushtenko")
                                .email("sergey@mail.com")
                                .password("922e22af7f9762d93d314d2b9deea821f8666581")
                                .salt("420f28d49532f09a")
                                .role(Role.USER)
                                .build())
                        .build())
        );
        return Stream.of(Arguments.of(
                        BookingRequestDto.newBuilder()
                                .userId(3)
                                .statusNotPaid("notPaid")
                                .statusPaid("paid")
                                .statusCanceled("canceled")
                                .build(), 0, new ArrayList<>(List.of(bookingList.get(5), bookingList.get(4))), "Check result for empty sorting and ordering"),
                Arguments.of(
                        BookingRequestDto.newBuilder()
                                .userId(2)
                                .statusPaid("paid")
                                .build(), 0, new ArrayList<>(List.of(bookingList.get(2))), "Check result for empty sorting and ordering"));
    }

    private static Stream<Arguments> provideDataForCheckCancelBookingById() {
        return Stream.of(
                Arguments.of(
                        BookingDto.newBuilder()
                                .id(1)
                                .checkIn(LocalDate.parse("2022-09-05"))
                                .checkOut(LocalDate.parse("2022-09-10"))
                                .room(Room.newBuilder()
                                        .id(5)
                                        .roomType(RoomType.newBuilder()
                                                .typeName("Single")
                                                .build())
                                        .numberOfBeds(1)
                                        .price(600)
                                        .hotel(Hotel.newBuilder()
                                                .name("Kyiv")
                                                .build())
                                        .build())
                                .nights(5)
                                .bookTime(LocalDate.parse("2022-09-03"))
                                .bookingStatusId(1)
                                .userId(1)
                                .build(), LocalDate.parse("2022-11-01"),
                        3,
                        "Test for cancel of booking"));
    }
}