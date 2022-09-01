package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.UsersOrderRequestDto;
import com.vladnickgofj.hotel.dao.UsersOrderDao;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.dao.entity.UsersOrder;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsersOrderDaoImpl extends AbstractCrudDaoImpl<UsersOrder> implements UsersOrderDao {
    private static final Logger LOGGER = Logger.getLogger(UsersOrderDaoImpl.class);
    private static final int PROCESSED_STATUS = 1;
    private static final int COMPLETED_STATUS = 2;
    private static final int CANCELED_STATUS = 3;
    private static final int ROOM_STATUS_STATEMENT_FREE = 1;
    private static final int ROOM_STATUS_STATEMENT_BOOKED = 2;
    private static final int ROOM_STATUS_STATEMENT_BUSY = 3;
    private static final int ROOM_STATUS_STATEMENT_NOT_AVAILABLE = 4;
    private static final int BOOKING_STATUS_NOT_PAID = 1;
    private static final int BOOKING_STATUS_PAID = 2;
    private static final int BOOKING_STATUS_CANCELED = 3;


    private static final String INSERT_INTO = "INSERT INTO " +
            "users_order(user_id, hotel_id, date_start, date_end, order_date, number_of_persons, room_type_id, order_status_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM users_order WHERE order_id=?;";
    private static final String FIND_ALL = "SELECT * FROM users_order; ";
    private static final String UPDATE_USERS_ORDER = "UPDATE users_order " +
            "SET order_status_id=? WHERE order_id=? ";
    public static final String COUNT_ALL_BY_PARAMETERS = "SELECT COUNT(order_id) AS number_of_orders " +
            "FROM users_order " +
            "         LEFT JOIN hotel h on h.hotel_id = users_order.hotel_id " +
            "         LEFT JOIN room_type rt on rt.type_id = users_order.room_type_id " +
            "         LEFT JOIN users_order_status uos on uos.order_status_id = users_order.order_status_id " +
            "         LEFT JOIN users u on u.user_id = users_order.user_id " +
            "%S ";
    public static final String FIND_ALL_BY_PARAMETERS = "SELECT * " +
            "FROM users_order " +
            "LEFT JOIN hotel h on h.hotel_id = users_order.hotel_id " +
            "LEFT JOIN room_type rt on rt.type_id = users_order.room_type_id " +
            "LEFT JOIN users_order_status uos on uos.order_status_id = users_order.order_status_id " +
            "LEFT JOIN users u on u.user_id = users_order.user_id " +
            "%S " +
            "ORDER BY %S %S " +
            "LIMIT ? OFFSET ?;";


    private static final String UPDATE_ROOM_STATUS_BY_PARAMETERS = "UPDATE room_status SET date_end=? WHERE status_id = ?";

    private static final String INSERT_INTO_ROOM_STATUS = "INSERT INTO " +
            "room_status (date_start, date_end, room_id, status_statement_id) " +
            "VALUES (?, ?, ?, ?), (?, ?, ?, ?)";

    private static final String INSERT_INTO_BOOKINGS = "INSERT INTO bookings " +
            "(check_in, check_out, room_id, book_time, booking_status_id, user_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";


    public UsersOrderDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_USERS_ORDER);
    }

    @Override
    protected UsersOrder mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToUsersOrder(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, UsersOrder entity) throws SQLException {
        preparedStatement.setInt(1, entity.getUser().getId());
        preparedStatement.setInt(2, entity.getHotel().getId());
        preparedStatement.setDate(3, Date.valueOf(entity.getDateStart()));
        preparedStatement.setDate(4, Date.valueOf(entity.getDateEnd()));
        preparedStatement.setDate(5, Date.valueOf(entity.getOrderDate()));
        preparedStatement.setInt(6, entity.getNumberOfPersons());
        preparedStatement.setInt(7, entity.getRoom().getRoomType().getId());
        preparedStatement.setInt(8, PROCESSED_STATUS);
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, UsersOrder entity) throws SQLException {
        preparedStatement.setInt(1, COMPLETED_STATUS);
        preparedStatement.setInt(2, entity.getId());
    }

    @Override
    public List<UsersOrder> findAll() {
        return null;
    }

    @Override
    public List<UsersOrder> findAllByParameters(UsersOrderRequestDto usersOrderRequestDto) {
        String querySubstitute = usersOrderRequestDto.getQuerySubstitute();
        String sorting = usersOrderRequestDto.getSorting();
        String ordering = usersOrderRequestDto.getOrdering();
        Integer itemsOnPage = usersOrderRequestDto.getItemsOnPage();
        Integer numberOfPage = usersOrderRequestDto.getNumberOfPage();

        int firstNumberOfPage = itemsOnPage * (numberOfPage - 1);
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(FIND_ALL_BY_PARAMETERS, querySubstitute, sorting, ordering))) {
            preparedStatement.setInt(1, itemsOnPage);
            preparedStatement.setInt(2, firstNumberOfPage);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<UsersOrder> entities = new HashSet<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return new ArrayList<>(entities);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public Integer countAll(UsersOrderRequestDto usersOrderRequestDto) {
        String querySubstitute = usersOrderRequestDto.getQuerySubstitute();

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(COUNT_ALL_BY_PARAMETERS, querySubstitute))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("number_of_orders");
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public void completeUsersOrder(List<UsersOrder> usersOrdersList, List<RoomStatus> roomStatusList) {
        try (Connection connection = connector.getConnection();
             PreparedStatement updateUsersOrderById = connection.prepareStatement("UPDATE users_order SET order_status_id=? WHERE order_id=? ");
             PreparedStatement updateRoomStatus = connection.prepareStatement(UPDATE_ROOM_STATUS_BY_PARAMETERS);
             PreparedStatement insertIntoRoomStatus = connection.prepareStatement(INSERT_INTO_ROOM_STATUS);
             PreparedStatement insertIntoBookings = connection.prepareStatement(INSERT_INTO_BOOKINGS)) {

            try {
                connection.setAutoCommit(false);
                updateUsersOrderById.setInt(1, COMPLETED_STATUS);
                updateUsersOrderById.setInt(2, usersOrdersList.get(0).getId());
                updateUsersOrderById.executeUpdate();

                for (int i = 0; i < usersOrdersList.size(); i++) {
                    updateRoomStatus.setDate(1, Date.valueOf(usersOrdersList.get(i).getDateStart()));
                    updateRoomStatus.setInt(2, roomStatusList.get(i).getId());
                    updateRoomStatus.executeUpdate();

                    insertIntoRoomStatus.setDate(1, Date.valueOf(usersOrdersList.get(i).getDateStart()));
                    insertIntoRoomStatus.setDate(2, Date.valueOf(usersOrdersList.get(i).getDateEnd()));
                    insertIntoRoomStatus.setInt(3, usersOrdersList.get(i).getRoom().getId());
                    insertIntoRoomStatus.setInt(4, ROOM_STATUS_STATEMENT_BOOKED);
                    insertIntoRoomStatus.setDate(5, Date.valueOf(usersOrdersList.get(i).getDateEnd()));
                    insertIntoRoomStatus.setDate(6, Date.valueOf(roomStatusList.get(i).getDateEnd()));
                    insertIntoRoomStatus.setInt(7, usersOrdersList.get(i).getRoom().getId());
                    insertIntoRoomStatus.setInt(8, ROOM_STATUS_STATEMENT_FREE);
                    insertIntoRoomStatus.execute();

                    insertIntoBookings.setDate(1, Date.valueOf(usersOrdersList.get(i).getDateStart()));
                    insertIntoBookings.setDate(2, Date.valueOf(usersOrdersList.get(i).getDateEnd()));
                    insertIntoBookings.setInt(3, usersOrdersList.get(i).getRoom().getId());
                    insertIntoBookings.setDate(4, Date.valueOf(LocalDate.now()));
                    insertIntoBookings.setInt(5, BOOKING_STATUS_NOT_PAID);
                    insertIntoBookings.setInt(6, usersOrdersList.get(i).getUser().getId());
                    insertIntoBookings.execute();
                }

                connection.commit();
                LOGGER.info("Transaction by update UsersOrder, Bookings, RoomStatus has been committed");
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.info("Rollback transaction by update UsersOrder, Bookings, RoomStatus");
                throw new DataBaseRuntimeException(e);
            }
        } catch (SQLException e) {
            LOGGER.info("SQLException" + e);
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public void updateUsersOrderById(Integer orderId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users_order SET order_status_id = ? WHERE order_id=?")) {
            preparedStatement.setInt(1, CANCELED_STATUS);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("SQLException" + e);
            throw new DataBaseRuntimeException(e);
        }
    }

}