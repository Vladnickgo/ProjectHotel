package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.entity.Booking;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDaoImpl extends AbstractCrudDaoImpl<Booking> {
    private static final String FIND_ALL = "SELECT * FROM bookings";
    private static final String FIND_BY_ID = "SELECT * FROM bookings WHERE booking_id=?";
    private static final String UPDATE_BOOKING = "UPDATE bookings SET check_in=?, check_out=?, room_id=?,night=?,book_time=?,booking_status_id=?  WHERE booking_id=?";
    private static final String INSERT_BOOKING = "INSERT INTO bookings( check_in, check_out, room_id,night, book_time, booking_status_id) VALUES (?,?,?,?,?)";

    public BookingDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_BOOKING, FIND_BY_ID, FIND_ALL, UPDATE_BOOKING);
    }

    @Override
    protected Booking mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Booking.newBuilder()
                .id(resultSet.getInt("booking_id"))
                .checkIn(resultSet.getDate("check_in"))
                .checkOut(resultSet.getDate("check_out"))
                .roomId(resultSet.getInt("room_id"))
                .night(resultSet.getInt("night"))
                .bookTime(resultSet.getDate("book_time"))
                .bookingStatusId(resultSet.getInt("booking_status_id"))
                .usersId(resultSet.getInt("user_id"))
                .build();


    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Booking entity) throws SQLException {
        preparedStatement.setDate(1, (Date) entity.getCheckIn());
        preparedStatement.setDate(2, (Date) entity.getCheckOut());
        preparedStatement.setInt(3, entity.getRoomId());
        preparedStatement.setInt(4, entity.getNight());
        preparedStatement.setDate(5, (Date) entity.getBookTime());
        preparedStatement.setInt(6, entity.getBookingStatusId());
        preparedStatement.setInt(7, entity.getUsersId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Booking entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(8, entity.getId());
    }

}
