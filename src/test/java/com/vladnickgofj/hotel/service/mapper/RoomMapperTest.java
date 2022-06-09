package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.RoomDto;
import com.vladnickgofj.hotel.dao.RoomDao;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.impl.RoomDaoImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomMapperTest {
    private final HikariConnectionPool connectionPool = new HikariConnectionPool("bd-config");
    private final RoomDao roomDao = new RoomDaoImpl(connectionPool);
    private final RoomMapper roomMapper = new RoomMapper();
    private final List<Room> roomsDao = roomDao.findAll();

    @Test
    void mapEntityToDto() {
        for (Room room : roomsDao) {
            int id = room.getId();
            RoomDto roomDto = getRoomDto(room, id);
            assertEquals(roomDto, roomMapper.mapEntityToDto(room));
        }
    }

    @Test
    void mapDtoToEntity() {
        for (Room room : roomsDao) {
            RoomDto roomDto = roomMapper.mapEntityToDto(room);
            Room roomDao = roomMapper.mapDtoToEntity(roomDto);
            assertEquals(room, roomDao);
        }
    }

    private RoomDto getRoomDto(Room room, int id) {
        return RoomDto.newBuilder()
                .id(id)
                .roomTypeId(room.getRoomType().getId())
                .numberOfBeds(room.getNumberOfBeds())
                .roomStatusId(room.getRoomStatus().getId())
                .price(room.getPrice())
                .hotelId(room.getHotel().getId())
                .build();
    }

}

