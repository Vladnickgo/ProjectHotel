package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.HotelDao;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDaoImpl extends AbstractCrudDaoImpl<Hotel> implements HotelDao {
    private final static String INSERT_INTO = "INSERT INTO hotel(name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM hotel WHERE hotel_id=?";
    private static final String FIND_ALL = "SELECT * FROM hotel";
    private static final String UPDATE_HOTEL = "UPDATE hotel SET name=? WHERE hotel_id=?";

    public HotelDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_HOTEL);
    }

    @Override
    protected Hotel mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToHotel(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Hotel entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Hotel entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(2, entity.getId());
    }
}
