package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.entity.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDaoImpl extends AbstractCrudDaoImpl<Room> {
    private final static String INSERT_INTO = "INSERT INTO room(type_id, number_of_beds, status_id, price, hotel_id) VALUES (?,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM room WHERE room_id=?";
    private static final String FIND_ALL = "SELECT * FROM room";
    private static final String UPDATE_USER = "UPDATE room SET type_id=?, number_of_beds=?, status_id=?, price=?, hotel_id=? WHERE room_id=?";

    public RoomDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_USER);
    }

    @Override
    protected Room mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Room(resultSet.getInt("room_id"),
                resultSet.getInt("type_id"),
                resultSet.getInt("number_of_beds"),
                resultSet.getInt("status_id"),
                resultSet.getInt("price"),
                resultSet.getInt("hotel_id"));
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Room entity) throws SQLException {
        preparedStatement.setInt(1, entity.getTypeId());
        preparedStatement.setInt(2, entity.getNumberOfBeds());
        preparedStatement.setInt(3, entity.getStatusId());
        preparedStatement.setInt(4, entity.getPrice());
        preparedStatement.setInt(5, entity.getHotelId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Room entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(6, entity.getId());
    }
}
