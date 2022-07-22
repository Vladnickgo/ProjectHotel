package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.BookingDao;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDaoImpl extends AbstractCrudDaoImpl<Booking> implements BookingDao {
    private static final String FIND_ALL = "SELECT * FROM bookings "+
    "LEFT JOIN room r ON r.room_id = bookings.room_id "+
    "LEFT JOIN booking_status bs ON bs.booking_status_id = bookings.booking_status_id "+
    "LEFT JOIN users u ON u.user_id = bookings.user_id "+
    "LEFT JOIN room_type rt ON rt.type_id = r.type_id "+
    "LEFT JOIN room_status rs ON rs.room_id= r.room_id "+
    "LEFT JOIN hotel h ON h.hotel_id = r.hotel_id ";
    private static final String FIND_BY_ID = "SELECT * " +
            "FROM bookings " +
            "         LEFT JOIN room r ON r.room_id = bookings.room_id " +
            "         LEFT JOIN bookings_status bs ON bs.booking_status_id = bookings.booking_status_id " +
            "         LEFT JOIN users u ON u.user_id = bookings.user_id " +
            "         LEFT JOIN room_type rt ON rt.type_id = r.type_id " +
            "         LEFT JOIN room_status rs ON rs.status_id = r.status_id " +
            "         LEFT JOIN hotel h ON h.hotel_id = r.hotel_id " +
            "WHERE booking_id=?";
    private static final String UPDATE_BOOKING = "UPDATE bookings SET " +
            "check_in=?, check_out=?, room_id=?,night=?,book_time=?,booking_status_id=? " +
            "WHERE booking_id=?";
    private static final String INSERT_BOOKING = "INSERT INTO "+
            "bookings(check_in, check_out, room_id,night, book_time, booking_status_id) VALUES (?,?,?,?,?)";

    public BookingDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_BOOKING, FIND_BY_ID, FIND_ALL, UPDATE_BOOKING);
    }

    @Override
    protected Booking mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToBooking(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Booking entity) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(entity.getCheckIn()));
        preparedStatement.setDate(2, Date.valueOf(entity.getCheckOut()));
        preparedStatement.setInt(3, entity.getRoom().getId());
        preparedStatement.setInt(4, entity.getNight());
        preparedStatement.setDate(5, Date.valueOf(entity.getBookTime()));
        preparedStatement.setInt(6, entity.getBookingStatus().getId());
        preparedStatement.setInt(7, entity.getUser().getId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Booking entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(8, entity.getId());
    }
}