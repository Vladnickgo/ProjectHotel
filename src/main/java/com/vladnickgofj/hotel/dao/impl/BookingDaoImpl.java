package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.BookingRequestDto;
import com.vladnickgofj.hotel.dao.BookingDao;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;
import com.vladnickgofj.hotel.service.util.BookingRequestDtoUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookingDaoImpl extends AbstractCrudDaoImpl<Booking> implements BookingDao {

    private static final Logger LOGGER = Logger.getLogger(BookingDaoImpl.class);

    private static final String FIND_ALL = "SELECT * FROM bookings " +
            "LEFT JOIN room r ON r.room_id = bookings.room_id " +
            "LEFT JOIN booking_status bs ON bs.booking_status_id = bookings.booking_status_id " +
            "LEFT JOIN users u ON u.user_id = bookings.user_id " +
            "LEFT JOIN room_type rt ON rt.type_id = r.type_id " +
            "LEFT JOIN room_status rs ON rs.room_id= r.room_id " +
            "LEFT JOIN hotel h ON h.hotel_id = r.hotel_id ";

    private static final String FIND_BY_ID = "SELECT * FROM bookings " +
            "LEFT JOIN room r on r.room_id = bookings.room_id " +
            "LEFT JOIN room_type rt on rt.type_id = r.type_id " +
            "LEFT JOIN hotel h on h.hotel_id = r.hotel_id " +
            "LEFT JOIN users u on u.user_id = bookings.user_id " +
            "LEFT JOIN booking_status bs on bs.booking_status_id = bookings.booking_status_id " +
            "WHERE booking_id = ?";

    private static final String FIND_BOOKING_BY_ROOM_ID_AND_DATES = "SELECT * FROM bookings " +
            "LEFT JOIN room r on r.room_id = bookings.room_id " +
            "LEFT JOIN room_type rt on rt.type_id = r.type_id " +
            "LEFT JOIN hotel h on h.hotel_id = r.hotel_id " +
            "LEFT JOIN users u on u.user_id = bookings.user_id " +
            "LEFT JOIN booking_status bs on bs.booking_status_id = bookings.booking_status_id " +
            "WHERE bookings.room_id = ? " +
            "AND bookings.booking_status_id = 1 " +
            "AND check_in = ? " +
            "AND check_out = ?";

    private static final String UPDATE_BOOKING = "UPDATE bookings SET " +
            "check_in=?, check_out=?, room_id=?,night=?,book_time=?,booking_status_id=? " +
            "WHERE booking_id=?";

    private static final String INSERT_BOOKING = "INSERT INTO " +
            "bookings(check_in, check_out, room_id,night, book_time, booking_status_id) VALUES (?,?,?,?,?)";

    private static final String UPDATE_BOOKING_STATUS_BY_ID = "UPDATE bookings SET " +
            "booking_status_id=? WHERE booking_id=? ";

    private static final String COUNT_ALL_BY_PARAMETERS = "SELECT count(booking_id) AS count_bookings " +
            "FROM bookings " +
            "WHERE user_id=? AND (bookings.booking_status_id = ? OR bookings.booking_status_id = ? OR bookings.booking_status_id = ?) ";

    private static final String FIND_ALL_BY_PARAMETERS = "SELECT * FROM bookings " +
            "LEFT JOIN room r on r.room_id = bookings.room_id " +
            "LEFT JOIN room_type rt on rt.type_id = r.type_id " +
            "LEFT JOIN booking_status bs on bs.booking_status_id = bookings.booking_status_id " +
            "LEFT JOIN users u on u.user_id = bookings.user_id " +
            "LEFT JOIN hotel h on h.hotel_id = r.hotel_id " +
            "WHERE bookings.user_id=? AND (bookings.booking_status_id = ? OR bookings.booking_status_id = ? OR bookings.booking_status_id = ?)" +
            "ORDER BY ? " +
            "LIMIT ? OFFSET ?;";

    private static final String INSERT_INTO_BOOKINGS = "INSERT INTO bookings " +
            "(check_in, check_out, room_id, book_time, booking_status_id, user_id) " +
            "VALUES (?, ?, ?, ?, 1, ?)";

    private static final String UPDATE_ROOM_STATUS_BY_PARAMETERS = "UPDATE room_status SET date_end=? WHERE status_id = ?";

    private static final String INSERT_INTO_ROOM_STATUS = "INSERT INTO " +
            "room_status (date_start, date_end, room_id, status_statement_id) " +
            "VALUES (?, ?, ?, 2), (?, ?, ?, 1)";

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


    @Override
    public Booking findBookingByParameters(BookingDto bookingDto) {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOOKING_BY_ROOM_ID_AND_DATES)) {
            preparedStatement.setInt(1, bookingDto.getRoom().getId());
            preparedStatement.setDate(2, Date.valueOf(bookingDto.getCheckIn()));
            preparedStatement.setDate(3, Date.valueOf(bookingDto.getCheckOut()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapResultSetToEntity(resultSet);
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Integer countAll(BookingRequestDto bookingRequestDto) {
        BookingRequestDtoUtil bookingRequestDtoUtil = new BookingRequestDtoUtil(bookingRequestDto);
        Integer[] bookingStatusIds = bookingRequestDtoUtil.getBookingStatusIds();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ALL_BY_PARAMETERS)) {
            preparedStatement.setInt(1, bookingRequestDto.getUserId());
            preparedStatement.setInt(2, bookingStatusIds[0]);
            preparedStatement.setInt(3, bookingStatusIds[1]);
            preparedStatement.setInt(4, bookingStatusIds[2]);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count_bookings");
        } catch (SQLException e) {
            LOGGER.info("Request not completed");
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public List<Booking> findBookingsByUserIdAndParameters(BookingRequestDto bookingRequestDto, Integer firstRecordOnPage) {
        BookingRequestDtoUtil bookingRequestDtoUtil = new BookingRequestDtoUtil(bookingRequestDto);
        String sortingAndOrdering = String.format("%s %s", bookingRequestDtoUtil.getSorting(), bookingRequestDtoUtil.getOrdering());
        Integer[] bookingStatusIds = bookingRequestDtoUtil.getBookingStatusIds();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_PARAMETERS)) {
            preparedStatement.setInt(1, bookingRequestDto.getUserId());
            preparedStatement.setInt(2, bookingStatusIds[0]);
            preparedStatement.setInt(3, bookingStatusIds[1]);
            preparedStatement.setInt(4, bookingStatusIds[2]);
            preparedStatement.setString(5, sortingAndOrdering);
            preparedStatement.setInt(6, bookingRequestDtoUtil.getItemsOnPage());
            preparedStatement.setInt(7, firstRecordOnPage);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Booking> entities = new HashSet<>();
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
    public void cancelBookingById(BookingDto byId, LocalDate dateEnd) {
        LocalDate checkIn = byId.getCheckIn();
        LocalDate checkOut = byId.getCheckOut();
        try (Connection connection = connector.getConnection();
             PreparedStatement updateBookingStatus = connection.prepareStatement(UPDATE_BOOKING_STATUS_BY_ID);
             PreparedStatement updateRoomStatus = connection.prepareStatement("UPDATE room_status SET date_end=? " +
                     "WHERE room_id=? AND status_statement_id=1 AND date_end=?");
             PreparedStatement deleteRoomStatusForBookedRange = connection.prepareStatement("DELETE FROM room_status " +
                     "WHERE room_id=? AND date_start=? AND date_end=? AND status_statement_id=2");
             PreparedStatement deleteRoomStatusForFreeRange = connection.prepareStatement("DELETE FROM room_status " +
                     "WHERE room_id=? AND date_start=? AND status_statement_id=1 ")
        ) {
            try {
                connection.setAutoCommit(false);
                updateBookingStatus.setInt(1, 3);
                updateBookingStatus.setInt(2, byId.getId());
                updateBookingStatus.executeUpdate();

                updateRoomStatus.setDate(1, Date.valueOf(dateEnd));
                updateRoomStatus.setInt(2, byId.getRoom().getId());
                updateRoomStatus.setDate(3, Date.valueOf(checkIn));
                updateRoomStatus.executeUpdate();

                deleteRoomStatusForBookedRange.setInt(1, byId.getRoom().getId());
                deleteRoomStatusForBookedRange.setDate(2, Date.valueOf(checkIn));
                deleteRoomStatusForBookedRange.setDate(3, Date.valueOf(checkOut));
                deleteRoomStatusForBookedRange.execute();

                deleteRoomStatusForFreeRange.setInt(1, byId.getRoom().getId());
                deleteRoomStatusForFreeRange.setDate(2, Date.valueOf(checkOut));
                deleteRoomStatusForFreeRange.execute();
                connection.commit();
                LOGGER.info("Order cancellation transaction completed");
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.info("Rollback of order cancellation transaction");
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }

    }

    @Override
    public void createNewBooking(Booking booking, RoomStatus roomStatus) {
        try (Connection connection = connector.getConnection();
             PreparedStatement insertIntoBookings = connection.prepareStatement(INSERT_INTO_BOOKINGS);
             PreparedStatement updateRoomStatus = connection.prepareStatement(UPDATE_ROOM_STATUS_BY_PARAMETERS);
             PreparedStatement insertIntoRoomStatus = connection.prepareStatement(INSERT_INTO_ROOM_STATUS)) {
            try {
                connection.setAutoCommit(false);

                insertIntoBookings(booking, insertIntoBookings);
                updateRoomStatus(booking, roomStatus, updateRoomStatus);
                insertIntoRoomStatus(roomStatus, booking, insertIntoRoomStatus);
                connection.commit();
                LOGGER.info("Transaction completed");
            } catch (SQLException e) {

                connection.rollback();
                LOGGER.info("Rollback transaction");
                throw new DataBaseRuntimeException(e);
            }
        } catch (SQLException e) {
            LOGGER.info("DataBaseRuntimeException");
            throw new DataBaseRuntimeException(e);
        }
    }

    private void insertIntoBookings(Booking booking, PreparedStatement insertIntoBookings) throws SQLException {
        insertIntoBookings.setDate(1, Date.valueOf(booking.getCheckIn()));
        insertIntoBookings.setDate(2, Date.valueOf(booking.getCheckOut()));
        insertIntoBookings.setInt(3, booking.getRoom().getId());
        insertIntoBookings.setDate(4, Date.valueOf(LocalDate.now()));
        insertIntoBookings.setInt(5, booking.getUser().getId());
        insertIntoBookings.execute();

    }

    private void insertIntoRoomStatus(RoomStatus roomStatus, Booking booking, PreparedStatement insertIntoRoomStatus) throws SQLException {
        insertIntoRoomStatus.setDate(1, Date.valueOf(booking.getCheckIn()));
        insertIntoRoomStatus.setDate(2, Date.valueOf(booking.getCheckOut()));
        insertIntoRoomStatus.setInt(3, roomStatus.getRoom().getId());
        insertIntoRoomStatus.setDate(4, Date.valueOf(booking.getCheckOut()));
        insertIntoRoomStatus.setDate(5, Date.valueOf(roomStatus.getDateEnd()));
        insertIntoRoomStatus.setInt(6, roomStatus.getRoom().getId());
        insertIntoRoomStatus.execute();
    }

    private void updateRoomStatus(Booking booking, RoomStatus roomStatus, PreparedStatement updateRoomStatus) throws SQLException {
        updateRoomStatus.setDate(1, Date.valueOf(booking.getCheckIn()));
        updateRoomStatus.setInt(2, roomStatus.getId());
        updateRoomStatus.executeUpdate();
    }

}