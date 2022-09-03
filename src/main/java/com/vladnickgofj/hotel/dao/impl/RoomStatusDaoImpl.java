package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;
import org.apache.log4j.Logger;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDtoRequest;
import com.vladnickgofj.hotel.dao.RoomStatusDao;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class RoomStatusDaoImpl extends AbstractCrudDaoImpl<RoomStatus> implements RoomStatusDao {

    private static final Logger LOGGER = Logger.getLogger(RoomStatusDaoImpl.class);

    private static final String INSERT_INTO = "INSERT INTO room_status(date_start, date_end, room_id, status_statement_id) VALUES (?,?,?,?)";

    private static final String FIND_BY_ID = "SELECT * FROM room_status " +
            "LEFT JOIN room r on r.room_id = room_status.room_id " +
            "LEFT OUTER JOIN room_type rt on rt.type_id = r.type_id " +
            "LEFT JOIN hotel h on h.hotel_id = r.hotel_id " +
            "LEFT JOIN room_status_statement rss on rss.status_statement_id = room_status.status_statement_id " +
            "WHERE status_id=?";

    private static final String FIND_ALL = "SELECT * FROM room_status";

    private static final String UPDATE_ROOM_STATUS = "UPDATE room_status SET date_start=?, date_end=?, room_id=?, status_statement_id=? WHERE status_id=?";

    private static final String FIND_ALL_BY_PARAMETERS = "SELECT * FROM room_status " +
            "LEFT JOIN room_status_statement rss on rss.status_statement_id = room_status.status_statement_id " +
            "LEFT JOIN room r on r.room_id = room_status.room_id " +
            "LEFT JOIN room_type rt on rt.type_id = r.type_id " +
            "LEFT JOIN hotel h on h.hotel_id = r.hotel_id " +
            "WHERE r.hotel_id=? " +
            "%S " +
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
            "AND date_start <= ? " +
            "AND date_end >= ? ";

    private static final String FIND_ALL_FREE_BY_PARAMETERS = "SELECT * " +
            "FROM room_status " +
            "LEFT JOIN room_status_statement rss on rss.status_statement_id = room_status.status_statement_id " +
            "LEFT JOIN room r on r.room_id = room_status.room_id " +
            "LEFT JOIN room_type rt on rt.type_id = r.type_id " +
            "LEFT JOIN hotel h on h.hotel_id = r.hotel_id " +
            "WHERE r.hotel_id = ? " +
            "AND ((date_start <= ? AND date_end >= ?) " +
            "    OR (date_start >= ? AND date_start < ?) " +
            "    OR (date_end > ? AND date_end <= ?)) " +
            "AND date_start != date_end " +
            "AND rss.status_statement_id=1 " +
            "ORDER BY price ";
//            "LIMIT ? OFFSET ?";

    private static final String FIND_FREE_BY_ROOM_ID_AND_DATE_START = "SELECT date_end FROM room_status WHERE room_id=? AND status_statement_id=1 AND date_start=? ";

    private static final String INSERT_INTO_BOOKINGS = "INSERT INTO bookings " +
            "(check_in, check_out, room_id, book_time, booking_status_id, user_id) " +
            "VALUES (?, ?, ?, ?, 1, ?)";

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
    public List<RoomStatus> findAll(RoomStatusDtoRequest roomStatusDtoRequest, Integer firstRoomOnPage) {
        Integer hotelId = roomStatusDtoRequest.getHotelId();
        LocalDate signIn = roomStatusDtoRequest.getSignIn();
        LocalDate signOut = roomStatusDtoRequest.getSignOut();
        Integer numberOnPage = roomStatusDtoRequest.getItemsOnPage();
        String sorting = roomStatusDtoRequest.getSorting();
        String ordering = roomStatusDtoRequest.getOrdering();
        String roomStatusQuerySubstitute = roomStatusDtoRequest.getQuerySubstitute();
        LOGGER.info("roomStatusQuerySubstitute" + roomStatusQuerySubstitute);
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(FIND_ALL_BY_PARAMETERS, roomStatusQuerySubstitute, sorting, ordering))) {
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setDate(2, Date.valueOf(signIn));
            preparedStatement.setObject(3, Date.valueOf(signOut));
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
    public Integer countAll(RoomStatusDtoRequest roomStatusDtoRequest) {
        String roomStatusQuerySubstitute = roomStatusDtoRequest.getQuerySubstitute();
        Integer hotelId = roomStatusDtoRequest.getHotelId();
        LocalDate signIn = roomStatusDtoRequest.getSignIn();
        LocalDate signOut = roomStatusDtoRequest.getSignOut();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(COUNT_ALL_BY_HOTEL_ID_AND_STATUS_ROOM, roomStatusQuerySubstitute))) {
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setDate(2, Date.valueOf(signIn));
            preparedStatement.setDate(3, Date.valueOf(signOut));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count_rooms");
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public List<RoomStatus> findAllFreeByParameters(UsersOrderDto usersOrderDto) {
        Integer hotelId = usersOrderDto.getHotelDto().getId();
        LocalDate dateStart = usersOrderDto.getDateStart();
        LocalDate dateEnd = usersOrderDto.getDateEnd();

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_FREE_BY_PARAMETERS)) {
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setDate(2, Date.valueOf(dateStart));
            preparedStatement.setDate(3, Date.valueOf(dateEnd));
            preparedStatement.setDate(4, Date.valueOf(dateStart));
            preparedStatement.setDate(5, Date.valueOf(dateEnd));
            preparedStatement.setDate(6, Date.valueOf(dateStart));
            preparedStatement.setDate(7, Date.valueOf(dateEnd));
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
    public LocalDate findFreeByRoomIdAndDateStart(BookingDto byId) {
        Integer id = byId.getRoom().getId();
        LocalDate checkOut = byId.getCheckOut();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_FREE_BY_ROOM_ID_AND_DATE_START)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, Date.valueOf(checkOut));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getDate("date_end").toLocalDate();
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }

    }

}