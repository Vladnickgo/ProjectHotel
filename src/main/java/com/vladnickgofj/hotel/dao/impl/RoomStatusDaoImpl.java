package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.RoomStatusDao;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoomStatusDaoImpl extends AbstractCrudDaoImpl<RoomStatus> implements RoomStatusDao {
    private final static String INSERT_INTO = "INSERT INTO room_status(date_start, date_end, room_id, status_statement_id) VALUES (?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM room_status WHERE status_id=?";
    private static final String FIND_ALL = "SELECT * FROM room_status";
    private static final String UPDATE_ROOM_STATUS = "UPDATE room_status SET date_start=?, date_end=?, room_id=?, status_statement_id=? WHERE status_id=?";
    private static final String FIND_ALL_BY_PARAMETERS = "SELECT * FROM room_status " +
            "LEFT JOIN room_status_statement rss on rss.status_statement_id = room_status.status_statement_id " +
            "LEFT JOIN room r on r.room_id = room_status.room_id " +
            "LEFT JOIN room_type rt on rt.type_id = r.type_id " +
            "LEFT JOIN hotel h on h.hotel_id = r.hotel_id " +
            "WHERE r.hotel_id=? " +
            "%S " +
//            "AND rss.status_statement_id=? " +
            "AND date_start <= ? " +
            "AND date_end >= ? " +
            "ORDER BY %S %S " +
            "LIMIT ? OFFSET ?";
    private static final String COUNT_ALL_BY_HOTEL_ID_AND_STATUS_ROOM = "SELECT COUNT(r.room_id) AS count_rooms FROM room_status " +
            "LEFT JOIN room_status_statement rss on rss.status_statement_id = room_status.status_statement_id " +
            "LEFT JOIN room r on r.room_id = room_status.room_id " +
            "LEFT JOIN room_type rt on rt.type_id = r.type_id " +
            "LEFT JOIN hotel h on h.hotel_id = r.hotel_id " +
            "WHERE r.hotel_id=? " +
            "%S " +
//            "AND rss.status_statement_id = ? " +
            "AND date_start <= ? " +
            "AND date_end >= ? ";

    public RoomStatusDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_ROOM_STATUS);
    }

    @Override
    protected RoomStatus mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToRoomStatus(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, RoomStatus entity) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(entity.getDateStart()));
        preparedStatement.setDate(2, Date.valueOf(entity.getDateEnd()));
        preparedStatement.setInt(3, entity.getRoom().getId());
        preparedStatement.setInt(4, entity.getStatusStatement().getStatusStatementId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, RoomStatus entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(5, entity.getId());
    }

    @Override
    public List<RoomStatus> findAll(Integer hotelId, RoomStatusDto roomStatus, String roomStatusQuerySubstitute, Integer numberOnPage, Integer firstRoomOnPage, String sorting, String ordering) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(FIND_ALL_BY_PARAMETERS, roomStatusQuerySubstitute, sorting, ordering))) {
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setDate(2, Date.valueOf(roomStatus.getDateStart()));
            preparedStatement.setObject(3, Date.valueOf(roomStatus.getDateEnd()));
            preparedStatement.setInt(4, numberOnPage);
            preparedStatement.setInt(5, firstRoomOnPage);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<RoomStatus> entities = new HashSet<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return new ArrayList<>(entities);
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Integer countAll(Integer hotelId, String roomStatusQuerySubstitute, RoomStatusDto roomStatus) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(COUNT_ALL_BY_HOTEL_ID_AND_STATUS_ROOM, roomStatusQuerySubstitute))) {
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setObject(2, roomStatus.getDateStart(), Types.DATE);
            preparedStatement.setObject(3, roomStatus.getDateEnd(), Types.DATE);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count_rooms");
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public void changeRoomStatus(RoomStatus roomStatus, Booking booking) {
        try (Connection connection = connector.getConnection();
             PreparedStatement updateRoomStatus = connection.prepareStatement("UPDATE room_status SET date_end=? WHERE status_id = ?");
             PreparedStatement insertIntoRoomStatus = connection.prepareStatement("INSERT INTO room_status (date_start, date_end, room_id, status_statement_id) VALUES (?, ?, ?, 2), (?, ?, ?, 1)");
             PreparedStatement insertIntoBookings = connection.prepareStatement("INSERT INTO bookings (check_in, check_out, room_id, book_time, booking_status_id, user_id) VALUES (?, ?, ?, ?, ?, ?)")) {
            try {
                connection.setAutoCommit(false);

                updateRoomStatus.setDate(1, Date.valueOf(roomStatus.getDateStart()));
                updateRoomStatus.setInt(2, roomStatus.getId());
                updateRoomStatus.executeUpdate();

                insertIntoRoomStatus.setDate(1, Date.valueOf(roomStatus.getDateStart()));
                insertIntoRoomStatus.setDate(2, Date.valueOf(roomStatus.getDateEnd()));
                insertIntoRoomStatus.setInt(3, roomStatus.getRoom().getId());
                insertIntoRoomStatus.setDate(4, Date.valueOf(roomStatus.getDateEnd()));
                insertIntoRoomStatus.setDate(5, Date.valueOf(LocalDate.now().plusMonths(3)));
                insertIntoRoomStatus.setInt(6, roomStatus.getRoom().getId());
                insertIntoRoomStatus.execute();

                insertIntoBookings.setDate(1, Date.valueOf(booking.getCheckIn()));
                insertIntoBookings.setDate(2, Date.valueOf(booking.getCheckOut()));
                insertIntoBookings.setInt(3, booking.getRoom().getId());
                insertIntoBookings.setDate(4, Date.valueOf(booking.getBookTime()));
                insertIntoBookings.setInt(5, booking.getBookingStatus().getId());
                insertIntoBookings.setInt(6, booking.getUser().getId());
                insertIntoBookings.execute();
                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

}