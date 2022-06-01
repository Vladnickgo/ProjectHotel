package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.RoomStatusDao;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomStatusDaoImpl extends AbstractCrudDaoImpl<RoomStatus> implements RoomStatusDao {
    private final static String INSERT_INTO = "INSERT INTO room_status(name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM room_status WHERE status_id=?";
    private static final String FIND_ALL = "SELECT * FROM room_status";
    private static final String UPDATE_ROOM_STATUS = "UPDATE room_status SET name=? WHERE status_id=?";

    public RoomStatusDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_ROOM_STATUS);
    }

    @Override
    protected RoomStatus mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToRoomStatus(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, RoomStatus entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, RoomStatus entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(2, entity.getId());
    }

}