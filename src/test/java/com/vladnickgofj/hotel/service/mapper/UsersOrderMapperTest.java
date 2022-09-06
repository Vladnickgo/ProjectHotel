package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.entity.*;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsersOrderMapperTest {
    Mapper<UsersOrderDto, UsersOrder> mapper = new UsersOrderMapper();

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideUsersOrdersAndUsersOrderDtoForCheckMapping")
    void checkMapDtoToEntity(UsersOrder usersOrder, UsersOrderDto usersOrderDto, String message) {
        assertEquals(usersOrder, mapper.mapDtoToEntity(usersOrderDto),message);
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideUsersOrderDtoAndUsersOrderForCheckMapping")
    void checkMapEntityToDto(UsersOrderDto usersOrderDto, UsersOrder usersOrder, String message) {
        assertEquals(usersOrderDto, mapper.mapEntityToDto(usersOrder), message);
    }

    private static Stream<Arguments> provideUsersOrdersAndUsersOrderDtoForCheckMapping() {
        return Stream.of(Arguments.of(UsersOrder.newBuilder()
                        .id(1)
                        .user(User.newBuilder()
                                .id(7)
                                .firstName("John")
                                .lastName("Doe")
                                .email("john@gmail.com")
                                .build())
                        .hotel(Hotel.newBuilder()
                                .id(5)
                                .build())
                        .dateStart(LocalDate.parse("2022-10-05"))
                        .dateEnd(LocalDate.parse("2022-10-15"))
                        .orderDate(LocalDate.parse("2022-10-01"))
                        .numberOfPersons(5)
                        .room(Room.newBuilder()
                                .id(5)
                                .roomType(RoomType.newBuilder()
                                        .id(3)
                                        .build())
                                .build())
                        .orderStatus(OrderStatus.COMPLETED)
                        .build(),
                UsersOrderDto.newBuilder()
                        .id(1)
                        .userDto(UserDto.newBuilder()
                                .id(7)
                                .firstName("John")
                                .lastName("Doe")
                                .email("john@gmail.com")
                                .build())
                        .hotelDto(HotelDto.newBuilder()
                                .id(5)
                                .build())
                        .dateStart(LocalDate.parse("2022-10-05"))
                        .dateEnd(LocalDate.parse("2022-10-15"))
                        .orderDate(LocalDate.parse("2022-10-01"))
                        .numberOfPersons(5)
                        .roomDtoResponse(RoomDtoResponse.newBuilder()
                                .id(5)
                                .roomType(com.vladnickgofj.hotel.controller.dto.RoomTypeDto.newBuilder()
                                        .typeId(3)
                                        .build())
                                .build())
                        .orderStatus(OrderStatus.COMPLETED)
                        .build(),
                "Mapping from Room to RoomDto is correct"
        ));

    }

    private static Stream<Arguments> provideUsersOrderDtoAndUsersOrderForCheckMapping() {
        return Stream.of(Arguments.of(UsersOrderDto.newBuilder()
                        .id(15)
                        .userDto(UserDto.newBuilder()
                                .id(7)
                                .firstName("John")
                                .lastName("Doe")
                                .email("john@gmail.com")
                                .build())
                        .hotelDto(HotelDto.newBuilder()
                                .id(5)
                                .name("Eleon")
                                .build())
                        .dateStart(LocalDate.parse("2022-10-05"))
                        .dateEnd(LocalDate.parse("2022-10-15"))
                        .orderDate(LocalDate.parse("2022-10-01"))
                        .numberOfPersons(5)
                        .roomDtoResponse(RoomDtoResponse.newBuilder()
//                                .id(4)
                                .roomType(com.vladnickgofj.hotel.controller.dto.RoomTypeDto.newBuilder()
                                        .typeId(3)
                                        .typeName("Double")
                                        .build())
                                .build())
                        .orderStatus(OrderStatus.COMPLETED)
                        .build(),
                UsersOrder.newBuilder()
                        .id(15)
                        .user(User.newBuilder()
                                .id(7)
                                .firstName("John")
                                .lastName("Doe")
                                .email("john@gmail.com")
                                .build())
                        .hotel(Hotel.newBuilder()
                                .id(5)
                                .name("Eleon")
                                .build())
                        .dateStart(LocalDate.parse("2022-10-05"))
                        .dateEnd(LocalDate.parse("2022-10-15"))
                        .orderDate(LocalDate.parse("2022-10-01"))
                        .numberOfPersons(5)
                        .room(Room.newBuilder()
//                                .id(4)
                                .roomType(RoomType.newBuilder()
                                        .id(3)
                                        .typeName("Double")
                                        .build())
                                .build())
                        .orderStatus(OrderStatus.COMPLETED)
                        .build(),
                "Mapping from OrderStatus to OrderStatusDto is correct"));
    }
}
