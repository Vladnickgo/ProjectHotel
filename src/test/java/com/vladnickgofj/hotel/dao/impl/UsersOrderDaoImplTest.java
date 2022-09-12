package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.UsersOrderRequestDto;
import com.vladnickgofj.hotel.dao.UsersOrderDao;
import com.vladnickgofj.hotel.dao.entity.*;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;
import com.vladnickgofj.hotel.service.util.UsersOrderRequestDtoUtil;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static com.vladnickgofj.hotel.dao.entity.OrderStatus.COMPLETED;
import static com.vladnickgofj.hotel.dao.entity.OrderStatus.PROCESSED;
import static org.junit.jupiter.api.Assertions.*;

class UsersOrderDaoImplTest {
    private static final Logger LOGGER = Logger.getLogger(UsersOrderDaoImplTest.class);
    private final HikariConnectionPool connectionPool = new HikariConnectionPool("db-config-test");

    @BeforeEach
    void setUp() {
        LOGGER.info("setup");
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()
        ) {

            try {
                connection.setAutoCommit(false);
                statement.execute("INSERT INTO testdb.users_order(order_id,user_id,hotel_id, date_start,date_end, order_date,number_of_persons,room_type_id,order_status_id) VALUES " +
                        "(1,2,1,'2022-09-11','2022-09-12','2022-09-11',1,6,2), " +
                        "(2,2,2,'2022-09-14','2022-09-17','2022-09-10',2,7,2), " +
                        "(3,3,1,'2022-09-15','2022-09-18','2022-09-10',3,3,1) ");
                statement.execute("INSERT INTO testdb.bookings(booking_id, check_in, check_out, room_id, book_time, booking_status_id, user_id) " +
                        "VALUES (1, '2022-09-11','2022-09-12', 1, '2022-09-11',1, 2), " +
                        "(2, '2022-09-14','2022-09-17', 1, '2022-09-11',1, 2)");
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

//    @AfterEach
//    void tearDown() {
//        try (Connection connection = connectionPool.getConnection();
//             Statement statement = connection.createStatement()
//        ) {
//
//            try {
//                connection.setAutoCommit(false);
//                statement.execute("DELETE FROM testdb.users_order WHERE order_id > 0 ");
//                statement.execute("DELETE FROM testdb.bookings WHERE booking_id > 0");
//                connection.commit();
//                LOGGER.info("Transaction completed");
//            } catch (SQLException e) {
//                connection.rollback();
//                LOGGER.info("Rollback of transaction");
//            }
//        } catch (SQLException e) {
//            throw new DataBaseRuntimeException(e);
//        }
//    }


    @ParameterizedTest(name = "[{index}{1}]")
    @MethodSource("provideListOfUsersOrdersForFindAllMethod")
    void checkFindAllMethod(List<UsersOrder> expectedList, String message) {
        UsersOrderDao usersOrderDao = new UsersOrderDaoImpl(connectionPool);
        List<UsersOrder> actualList = usersOrderDao.findAll().stream().
                sorted(Comparator.comparing(UsersOrder::getId, Comparator.naturalOrder()))
                .collect(Collectors.toList());
        assertEquals(expectedList, actualList, message);
    }


    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideListOfUsersOrdersForFindAllByParametersMethod")
    void findAllByParameters(UsersOrderRequestDtoUtil usersOrderRequestDtoUtil, List<UsersOrder> expectedList, String message) {
        UsersOrderDao usersOrderDao = new UsersOrderDaoImpl(connectionPool);
        List<UsersOrder> actualList = usersOrderDao.findAllByParameters(usersOrderRequestDtoUtil).stream()
                .sorted(Comparator.comparing(UsersOrder::getId, Comparator.naturalOrder()))
                .collect(Collectors.toList());
        assertEquals(expectedList, actualList, message);

    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideDataForCountAllMethod")
    void countAll(UsersOrderRequestDtoUtil usersOrderRequestDtoUtil, Integer expected, String message) {
        UsersOrderDao usersOrderDao = new UsersOrderDaoImpl(connectionPool);
        Integer actual = usersOrderDao.countAll(usersOrderRequestDtoUtil);
        assertEquals(expected, actual, message);
    }

    @Test
    void completeUsersOrder() {
    }

    @Test
    void updateUsersOrderById() {
    }

    private static Stream<Arguments> provideListOfUsersOrdersForFindAllMethod() {
        List<UsersOrder> usersOrderList = new ArrayList<>(List.of(
                UsersOrder.newBuilder()
                        .id(1)
                        .user(User.newBuilder()
                                .id(2)
                                .firstName("Andrey")
                                .lastName("Mastykov")
                                .email("andrey@mail.com")
                                .password("0a233ec67ca2b482e6d3e3c62941135d1b660199")
                                .salt("e8afd5d44e2be783")
                                .role(Role.USER)
                                .build())
                        .hotel(Hotel.newBuilder()
                                .id(1)
                                .name("Eleon")
                                .build())
                        .dateStart(LocalDate.parse("2022-09-11"))
                        .dateEnd(LocalDate.parse("2022-09-12"))
                        .orderDate(LocalDate.parse("2022-09-11"))
                        .numberOfPersons(1)
                        .roomType(RoomType.newBuilder()
                                .id(6)
                                .typeName("King")
                                .build())
                        .orderStatus(COMPLETED)
                        .build(),
                UsersOrder.newBuilder()
                        .id(2)
                        .user(User.newBuilder()
                                .id(2)
                                .firstName("Andrey")
                                .lastName("Mastykov")
                                .email("andrey@mail.com")
                                .password("0a233ec67ca2b482e6d3e3c62941135d1b660199")
                                .salt("e8afd5d44e2be783")
                                .role(Role.USER)
                                .build())
                        .hotel(Hotel.newBuilder()
                                .id(2).
                                name("Kyiv")
                                .build())
                        .dateStart(LocalDate.parse("2022-09-14"))
                        .dateEnd(LocalDate.parse("2022-09-17"))
                        .orderDate(LocalDate.parse("2022-09-10"))
                        .numberOfPersons(2)
                        .roomType(RoomType.newBuilder()
                                .id(7)
                                .typeName("Twin")
                                .build())
                        .orderStatus(COMPLETED)
                        .build(),
                UsersOrder.newBuilder()
                        .id(3)
                        .user(User.newBuilder()
                                .id(3)
                                .firstName("Sergey")
                                .lastName("Galushtenko")
                                .email("sergey@mail.com")
                                .password("922e22af7f9762d93d314d2b9deea821f8666581")
                                .salt("420f28d49532f09a")
                                .role(Role.USER)
                                .build())
                        .hotel(Hotel.newBuilder()
                                .id(1)
                                .name("Eleon")
                                .build())
                        .dateStart(LocalDate.parse("2022-09-15"))
                        .dateEnd(LocalDate.parse("2022-09-18"))
                        .orderDate(LocalDate.parse("2022-09-10"))
                        .numberOfPersons(3)
                        .roomType(RoomType.newBuilder()
                                .id(3)
                                .typeName("Triple")
                                .build())
                        .orderStatus(PROCESSED)
                        .build()));

        return Stream.of(Arguments.of(usersOrderList, "Test for findAll method"));
    }

    @NotNull
    private static Stream<Arguments> provideDataForCountAllMethod() {
        return Stream.of(
                Arguments.of(new UsersOrderRequestDtoUtil(UsersOrderRequestDto.newBuilder()
                        .statusNotDone("notDone")
                        .build()), 1, "Tests for countAll method with parameter \"notDone\""),
                Arguments.of(new UsersOrderRequestDtoUtil(UsersOrderRequestDto.newBuilder()
                        .statusCompleted("completed")
                        .build()), 2, "Tests for countAll method with parameter \"completed\"")

        );
    }

    @NotNull
    private static Stream<Arguments> provideListOfUsersOrdersForFindAllByParametersMethod() {
        return Stream.of(
                Arguments.of(new UsersOrderRequestDtoUtil(UsersOrderRequestDto.newBuilder()
                                .statusNotDone("notDone")
                                .build()),
                        new ArrayList<>(List.of(
                                UsersOrder.newBuilder()
                                        .id(3)
                                        .user(User.newBuilder()
                                                .id(3)
                                                .firstName("Sergey")
                                                .lastName("Galushtenko")
                                                .email("sergey@mail.com")
                                                .password("922e22af7f9762d93d314d2b9deea821f8666581")
                                                .salt("420f28d49532f09a")
                                                .role(Role.USER)
                                                .build())
                                        .hotel(Hotel.newBuilder()
                                                .id(1)
                                                .name("Eleon")
                                                .build())
                                        .dateStart(LocalDate.parse("2022-09-15"))
                                        .dateEnd(LocalDate.parse("2022-09-18"))
                                        .orderDate(LocalDate.parse("2022-09-10"))
                                        .numberOfPersons(3)
                                        .roomType(RoomType.newBuilder()
                                                .id(3)
                                                .typeName("Triple")
                                                .build())
                                        .orderStatus(PROCESSED)
                                        .build())),
                        "Tests for findAll method with parameter for order status \"not done\""),
                Arguments.of(new UsersOrderRequestDtoUtil(UsersOrderRequestDto.newBuilder()
                                .statusCompleted("completed")
                                .build()), new ArrayList<>(List.of(
                                UsersOrder.newBuilder()
                                        .id(1)
                                        .user(User.newBuilder()
                                                .id(2)
                                                .firstName("Andrey")
                                                .lastName("Mastykov")
                                                .email("andrey@mail.com")
                                                .password("0a233ec67ca2b482e6d3e3c62941135d1b660199")
                                                .salt("e8afd5d44e2be783")
                                                .role(Role.USER)
                                                .build())
                                        .hotel(Hotel.newBuilder()
                                                .id(1)
                                                .name("Eleon")
                                                .build())
                                        .dateStart(LocalDate.parse("2022-09-11"))
                                        .dateEnd(LocalDate.parse("2022-09-12"))
                                        .orderDate(LocalDate.parse("2022-09-11"))
                                        .numberOfPersons(1)
                                        .roomType(RoomType.newBuilder()
                                                .id(6)
                                                .typeName("King")
                                                .build())
                                        .orderStatus(COMPLETED)
                                        .build(),
                                UsersOrder.newBuilder()
                                        .id(2)
                                        .user(User.newBuilder()
                                                .id(2)
                                                .firstName("Andrey")
                                                .lastName("Mastykov")
                                                .email("andrey@mail.com")
                                                .password("0a233ec67ca2b482e6d3e3c62941135d1b660199")
                                                .salt("e8afd5d44e2be783")
                                                .role(Role.USER)
                                                .build())
                                        .hotel(Hotel.newBuilder()
                                                .id(2).
                                                name("Kyiv")
                                                .build())
                                        .dateStart(LocalDate.parse("2022-09-14"))
                                        .dateEnd(LocalDate.parse("2022-09-17"))
                                        .orderDate(LocalDate.parse("2022-09-10"))
                                        .numberOfPersons(2)
                                        .roomType(RoomType.newBuilder()
                                                .id(7)
                                                .typeName("Twin")
                                                .build())
                                        .orderStatus(COMPLETED)
                                        .build())),
                        "Tests for findAll method with parameter for order status \"completed\"")

        );
    }
}