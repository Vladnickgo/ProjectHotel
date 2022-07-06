package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.RoomTypeDto;
import com.vladnickgofj.hotel.dao.RoomTypeDao;
import com.vladnickgofj.hotel.dao.entity.RoomType;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoomTypeServiceImplTest {

    @Mock
    private RoomTypeDao roomTypeDao;

    @Mock
    private Mapper<RoomTypeDto, RoomType> mapper;

    @InjectMocks
    private RoomTypeServiceImpl roomTypeService;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideNotExistIdForCheckingGetRoomById")
    void checkGetRoomTypeById_Exception(Integer id, String expectedMessage, String message) {
        Mockito.when(roomTypeDao.findById(id)).thenReturn(Optional.empty());
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> roomTypeService.getRoomTypeById(id));
        String actualMessage = illegalArgumentException.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideRoomTypeAndRoomTypeDto")
    void checkGetRoomTypeById_ForExistRoomType(Integer id, RoomType roomType, RoomTypeDto roomTypeDto, String message) {
        Mockito.when(roomTypeDao.findById(id)).thenReturn(Optional.ofNullable(roomType));
        Mockito.when(mapper.mapEntityToDto(roomType)).thenReturn(roomTypeDto);
        RoomTypeDto actualRoomTypeById = roomTypeService.getRoomTypeById(id);
        assertEquals(actualRoomTypeById, roomTypeDto);
    }

    private static Stream<Arguments> provideNotExistIdForCheckingGetRoomById() {
        return Stream.of(Arguments.of(15, "Room type with id<strong>15</strong> not defined", "roomTypeId = 15"),
                Arguments.of(105, "Room type with id<strong>105</strong> not defined", "roomTypeId = 105"),
                Arguments.of(0, "Room type with id<strong>0</strong> not defined", "roomTypeId = 0"),
                Arguments.of(null, "Room type with id<strong>null</strong> not defined", "roomTypeId = null")
        );
    }

    private static Stream<Arguments> provideRoomTypeAndRoomTypeDto() {
        return Stream.of(Arguments.of(5,
                        RoomType.newBuilder()
                                .id(5)
                                .typeName("Queen")
                                .build(),
                        RoomTypeDto.newBuilder()
                                .typeId(5)
                                .typeName("Queen")
                                .build(),
                        "id = 5, RoomType = 'Queen'"),
                Arguments.of(6,
                        RoomType.newBuilder()
                                .id(6)
                                .typeName("King")
                                .build(),
                        RoomTypeDto.newBuilder()
                                .typeId(6)
                                .typeName("King")
                                .build(),
                        "id = 6, RoomType = 'King'")

        );
    }
}