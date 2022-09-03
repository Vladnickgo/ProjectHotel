package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.BookingStatusDao;
import com.vladnickgofj.hotel.dao.entity.BookingStatus;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BookingStatusDaoImpl extends AbstractCrudDaoImpl<BookingStatus> implements BookingStatusDao {
    private final static String INSERT_INTO = "INSERT INTO bookings_status(booking_status_name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM bookings_status WHERE booking_status_id=?";
    private static final String FIND_ALL = "SELECT * FROM bookings_status";
    private static final String UPDATE_BOOKING_STATUS = "UPDATE bookings_status SET booking_status_name=? " +
            "WHERE bookings_status_id=?";

    public BookingStatusDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_BOOKING_STATUS);
    }

    @Override
    protected BookingStatus mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToBookingStatus(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, BookingStatus entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, BookingStatus entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(2, entity.getId());
    }
}
