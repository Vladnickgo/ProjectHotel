package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.PaymentDao;
import com.vladnickgofj.hotel.dao.entity.Payment;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDaoImpl extends AbstractCrudDaoImpl<Payment> implements PaymentDao {

    private final static String INSERT_INTO = "INSERT INTO payments(booking_id, user_id, amount) VALUES (?,?,?) ";
    private static final String FIND_BY_ID = "SELECT * " +
            "FROM payments " +
            "         LEFT JOIN bookings b ON b.booking_id = payments.booking_id " +
            "         LEFT JOIN users u ON u.user_id = b.user_id " +
            "         LEFT JOIN room r ON r.room_id = b.room_id " +
            "         LEFT JOIN room_type rt ON rt.type_id = r.type_id " +
            "         LEFT JOIN room_status rs ON rs.status_id = r.status_id " +
            "         LEFT JOIN hotel h ON h.hotel_id = r.hotel_id " +
            "         LEFT JOIN bookings_status bs ON bs.booking_status_id = b.booking_status_id " +
            "WHERE payment_id=?";


    private static final String FIND_ALL = "SELECT * " +
            "FROM payments " +
            "         LEFT JOIN bookings b ON b.booking_id = payments.booking_id " +
            "         LEFT JOIN users u ON u.user_id = b.user_id " +
            "         LEFT JOIN room r ON r.room_id = b.room_id " +
            "         LEFT JOIN room_type rt ON rt.type_id = r.type_id " +
            "         LEFT JOIN room_status rs ON rs.status_id = r.status_id " +
            "         LEFT JOIN hotel h ON h.hotel_id = r.hotel_id " +
            "         LEFT JOIN bookings_status bs ON bs.booking_status_id = b.booking_status_id";

    private static final String UPDATE_PAYMENT = "UPDATE payments "+
            "SET booking_id=?, user_id=?, amount=? WHERE payment_id=?";

    public PaymentDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_PAYMENT);
    }

    @Override
    protected Payment mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToPayment(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Payment entity) throws SQLException {
        preparedStatement.setInt(1, entity.getBooking().getId());
        preparedStatement.setInt(2, entity.getUser().getId());
        preparedStatement.setInt(3, entity.getAmount());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Payment entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(4, entity.getAmount());
    }
}
