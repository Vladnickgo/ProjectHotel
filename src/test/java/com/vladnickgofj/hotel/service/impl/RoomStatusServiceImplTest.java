package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.RoomDtoResponse;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.RoomStatusDao;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.entity.StatusStatement;
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

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoomStatusServiceImplTest {

    @Mock
    RoomStatusDao roomStatusRepository;

    @Mock
    Mapper<RoomStatusDto, RoomStatus> mapper;

    @InjectMocks
    RoomStatusServiceImpl roomStatusService;

    @ParameterizedTest(name = "[{index}]{2}")
    @MethodSource("provideNotExistIdForCheckingGetRoomStatusById")
    void checkGetRoomStatusById_Exception(Integer id, String expectedMessage, String message) {
        Mockito.when(roomStatusRepository.findById(id)).thenReturn(Optional.empty());
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> roomStatusService.getRoomStatusById(id));
        String actualMessage = illegalArgumentException.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideRoomStatusAndRoomStatusDto")
    void checkGetRoomStatusById_Exception_ForExistRoomStatus(
            Integer id, RoomStatus roomStatus, RoomStatusDto roomStatusDto, String message) {
        Mockito.when(roomStatusRepository.findById(id)).thenReturn(Optional.ofNullable(roomStatus));
        Mockito.when(mapper.mapEntityToDto(roomStatus)).thenReturn(roomStatusDto);
        RoomStatusDto actualRoomStatusDto = roomStatusService.getRoomStatusById(id);
        assertEquals(roomStatusDto, actualRoomStatusDto);

    }

    private static Stream<Arguments> provideNotExistIdForCheckingGetRoomStatusById() {
        return Stream.of(
                Arguments.of(3,
                        "Room status with id <strong>3</strong> not defined",
                        "roomStatusId = 3"),
                Arguments.of(4,
                        "Room status with id <strong>4</strong> not defined",
                        "roomStatusId = 4"),
                Arguments.of(5,
                        "Room status with id <strong>5</strong> not defined",
                        "roomStatusId = 5"),
                Arguments.of(0,
                        "Room status with id <strong>0</strong> not defined",
                        "roomStatusId = 0")

        );
    }

    private static Stream<Arguments> provideRoomStatusAndRoomStatusDto() {
        return Stream.of(
                Arguments.of(1,
                        RoomStatus.newBuilder()
                                .id(1)
                                .dateStart(LocalDate.of(2022, 6, 1))
                                .dateEnd(LocalDate.of(2022, 6, 10))
                                .room(Room.newBuilder()
                                        .id(25)
                                        .build())
                                .statusStatement(StatusStatement.newBuilder()
                                        .statusStatementId(1)
                                        .build())
                                .build(),
                        RoomStatusDto.newBuilder()
                                .statusId(1)
                                .dateStart(LocalDate.of(2022, 6, 1))
                                .dateEnd(LocalDate.of(2022, 6, 10))
                                .roomDtoResponse(RoomDtoResponse.newBuilder()
                                        .id(25)
                                        .build())
                                .statusStatement(StatusStatement.newBuilder()
                                        .statusStatementId(1)
                                        .build())
                                .build(),
                        "check for roomStatus free"),
                Arguments.of(1,
                        RoomStatus.newBuilder()
                                .id(100)
                                .dateStart(LocalDate.of(2022, 6, 1))
                                .dateEnd(LocalDate.of(2022, 6, 10))
                                .room(Room.newBuilder()
                                        .id(55)
                                        .build())
                                .statusStatement(StatusStatement.newBuilder()
                                        .statusStatementId(2)
                                        .build())
                                .build(),
                        RoomStatusDto.newBuilder()
                                .statusId(100)
                                .dateStart(LocalDate.of(2022, 6, 1))
                                .dateEnd(LocalDate.of(2022, 6, 10))
                                .roomDtoResponse(RoomDtoResponse.newBuilder()
                                        .id(55)
                                        .build())
                                .statusStatement(StatusStatement.newBuilder()
                                        .statusStatementId(2)
                                        .build())
                                .build(),
                        "check for roomStatus booked"));
    }
}