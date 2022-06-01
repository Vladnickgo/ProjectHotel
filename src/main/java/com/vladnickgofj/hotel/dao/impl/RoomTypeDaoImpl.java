package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.RoomTypeDao;
import com.vladnickgofj.hotel.dao.entity.RoomType;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RoomTypeDaoImpl extends AbstractCrudDaoImpl<RoomType> implements RoomTypeDao {
    private final static String INSERT_INTO = "INSERT INTO room_type(type_name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM room_type WHERE type_id=?";
    private static final String FIND_ALL = "SELECT * FROM room_type";
    private static final String UPDATE_ROOM = "UPDATE room_type SET type_name=? WHERE type_id=?";

    public RoomTypeDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_ROOM);
    }

    @Override
    protected RoomType mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToRoomType(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, RoomType entity) throws SQLException {
        preparedStatement.setString(1, entity.getTypeName());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, RoomType entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(2, entity.getId());
    }
}
