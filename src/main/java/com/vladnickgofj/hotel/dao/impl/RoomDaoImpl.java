package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.RoomDao;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDaoImpl extends AbstractCrudDaoImpl<Room> implements RoomDao {
    private final static String INSERT_INTO = "INSERT INTO " +
            "room(type_id, number_of_beds, status_id, price, hotel_id) VALUES (?,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM room " +
            "LEFT OUTER JOIN room_type rt on rt.type_id = room.type_id " +
            "LEFT JOIN room_status rs on rs.status_id = room.status_id " +
            "LEFT JOIN hotel h on h.hotel_id = room.hotel_id " +
            "WHERE room_id=?;";
    private static final String FIND_ALL = "SELECT * FROM room " +
            "LEFT OUTER JOIN room_type rt on rt.type_id = room.type_id " +
            "LEFT JOIN room_status rs on rs.status_id = room.status_id " +
            "LEFT JOIN hotel h on h.hotel_id = room.hotel_id;";
    private static final String UPDATE_ROOM = "UPDATE room " +
            "SET type_id=?, number_of_beds=?, status_id=?, price=?, hotel_id=? WHERE room_id=?";

    public RoomDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_ROOM);
    }

    @Override
    protected Room mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToRoom(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Room entity) throws SQLException {
        preparedStatement.setInt(1, entity.getRoomType().getId());
        preparedStatement.setInt(2, entity.getNumberOfBeds());
        preparedStatement.setInt(3, entity.getRoomStatus().getId());
        preparedStatement.setInt(4, entity.getPrice());
        preparedStatement.setInt(5, entity.getHotel().getId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Room entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(6, entity.getId());
    }
}
