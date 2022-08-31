package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class HotelMapperTest {
    private final Mapper<HotelDto, Hotel> mapper = new HotelMapper();

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideHotelAndHotelDtoForCheckMapping")
    public void checkMapDtoToEntity(Hotel hotel, HotelDto hotelDto, String message) {
        Assertions.assertEquals(hotel, mapper.mapDtoToEntity(hotelDto), message);
    }

    @ParameterizedTest(name = "[{index}{2}]")
    @MethodSource("provideHotelDtoAndHotelForCheckMapping")
    public void checkMapEntityToDto(HotelDto hotelDto, Hotel hotel, String message) {
        Assertions.assertEquals(hotelDto, mapper.mapEntityToDto(hotel), message);
    }

    private static Stream<Arguments> provideHotelAndHotelDtoForCheckMapping() {
        return Stream.of(Arguments.of(Hotel.newBuilder()
                                .id(5)
                                .name("Nota")
                                .build(),
                        HotelDto.newBuilder()
                                .id(5)
                                .name("Nota")
                                .build(),
                        "Mapping for hotel \"Nota\""
                ), Arguments.of(Hotel.newBuilder().id(123)
                                .name("Сьоме небо")
                                .build(),
                        HotelDto.newBuilder()
                                .id(123)
                                .name("Сьоме небо")
                                .build(),
                        "Mapping for hotel \"Сьоме небо\""
                )
        );
    }

    private static Stream<Arguments> provideHotelDtoAndHotelForCheckMapping() {
        return Stream.of(Arguments.of(HotelDto.newBuilder()
                        .id(55)
                        .name("Leo")
                        .build(),
                Hotel.newBuilder()
                        .id(55)
                        .name("Leo")
                        .build(),
                "Mapping for hotel \"Leo\""
        ), Arguments.of(HotelDto.newBuilder()
                        .id(23)
                        .name("Весела бдЖілка")
                        .build(),
                Hotel.newBuilder().id(23)
                        .name("Весела бдЖілка")
                        .build(),
                "Mapping for hotel \"Весела бдЖілка\""
        ));
    }
}