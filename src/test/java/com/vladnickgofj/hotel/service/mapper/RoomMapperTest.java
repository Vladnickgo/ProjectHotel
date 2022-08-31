package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.RoomDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomMapperTest {
    private final Mapper<RoomDto, Room> mapper = new RoomMapper();

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideRoomAndRoomDtoForCheckMapping")
    void checkMapEntityToDto(Room room, RoomDto roomDto, String message) {
        assertEquals(roomDto, mapper.mapEntityToDto(room));
    }

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideRoomDtoAndRoomForCheckMapping")
    void checkMapDtoToEntity(RoomDto roomDto, Room room, String message) {
        assertEquals(room, mapper.mapDtoToEntity(roomDto));
    }

    private static Stream<Arguments> provideRoomAndRoomDtoForCheckMapping() {
        return Stream.of(
                Arguments.of(Room.newBuilder()
                                .id(1)
                                .roomType(RoomType.newBuilder()
                                        .id(2)
                                        .typeName("suite")
                                        .build())
                                .numberOfBeds(2)
                                .price(350)
                                .hotel(Hotel.newBuilder()
                                        .id(1)
                                        .name("Kyiv")
                                        .build())
                                .build(),

                        RoomDto.newBuilder()
                                .id(1)
                                .roomTypeId(2)
                                .numberOfBeds(2)
                                .price(350)
                                .hotelId(1)
                                .build(),
                        "Mapping from Room to RoomDto is correct"),
                Arguments.of(Room.newBuilder()
                                .id(2)
                                .roomType(RoomType.newBuilder()
                                        .id(2)
                                        .typeName("suite")
                                        .build())
                                .numberOfBeds(3)
                                .price(550)
                                .hotel(Hotel.newBuilder()
                                        .id(2)
                                        .name("Eleon")
                                        .build())
                                .build(),

                        RoomDto.newBuilder()
                                .id(2)
                                .roomTypeId(2)
                                .numberOfBeds(3)
                                .price(550)
                                .hotelId(2)
                                .build(),
                        "Mapping from Room to RoomDto is correct")
        );
    }

    private static Stream<Arguments> provideRoomDtoAndRoomForCheckMapping() {
        return Stream.of(
                Arguments.of(
                        RoomDto.newBuilder()
                                .id(1)
                                .roomTypeId(2)
                                .numberOfBeds(2)
                                .price(350)
                                .hotelId(1)
                                .build(),
                        Room.newBuilder()
                                .id(1)
                                .roomType(RoomType.newBuilder()
                                        .id(2)
                                        .build())
                                .numberOfBeds(2)
                                .price(350)
                                .hotel(Hotel.newBuilder()
                                        .id(1)
                                        .build())
                                .build(),
                        "Mapping from RoomDto to Room is correct"),
                Arguments.of(
                        RoomDto.newBuilder()
                                .id(2)
                                .roomTypeId(2)
                                .numberOfBeds(3)
                                .price(550)
                                .hotelId(2)
                                .build(),
                        Room.newBuilder()
                                .id(2)
                                .roomType(RoomType.newBuilder()
                                        .id(2)
                                        .build())
                                .numberOfBeds(3)
                                .price(550)
                                .hotel(Hotel.newBuilder()
                                        .id(2)
                                        .build())
                                .build(),
                        "Mapping from RoomDto to Room is correct"));
    }
}

