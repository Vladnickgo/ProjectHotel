package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.RoomDao;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.vladnickgofj.hotel.dao.mapper.ResultSetMapper.mapResultSetToRoom;

public class RoomDaoImpl extends AbstractCrudDaoImpl<Room> implements RoomDao {
    private final static String INSERT_INTO = "INSERT INTO " +
            "room(type_id, number_of_beds, price, hotel_id) VALUES (?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM room " +
            "LEFT OUTER JOIN room_type rt on rt.type_id = room.type_id " +
            "LEFT JOIN room_status rs on rs.status_id = room.status_id " +
            "LEFT JOIN hotel h on h.hotel_id = room.hotel_id " +
            "WHERE room_id=?;";
    private static final String FIND_ALL = "SELECT * FROM room " +
            "LEFT OUTER JOIN room_type rt on rt.type_id = room.type_id " +
            "LEFT JOIN room_status rs on rs.status_id = room.status_id " +
            "LEFT JOIN hotel h on h.hotel_id = room.hotel_id;";
    private static final String UPDATE_ROOM = "UPDATE room " +
            "SET type_id=?, number_of_beds=?, status_id=?, price=?, hotel_id=? WHERE room_id=?";
    public static final String FIND_ALL_BY_HOTEL_ID_PARAMETER = "SELECT * FROM room " +
            "LEFT JOIN room_type rt on room.type_id = rt.type_id " +
            "LEFT JOIN room_status rs on room.room_id = rs.room_id " +
            "LEFT OUTER JOIN hotel h on h.hotel_id = room.hotel_id " +
            "WHERE room.hotel_id=? " +
            "ORDER BY %S %S " +
            "LIMIT ? OFFSET ?";
    private static final String COUNT_ALL_BY_HOTEL_ID = "SELECT COUNT(room_id) AS count_rooms FROM room WHERE hotel_id=?";

    public RoomDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_ROOM);
    }

    @Override
    protected Room mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return mapResultSetToRoom(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, Room entity) throws SQLException {
        preparedStatement.setInt(1, entity.getRoomType().getId());
        preparedStatement.setInt(2, entity.getNumberOfBeds());
        preparedStatement.setInt(3, entity.getPrice());
        preparedStatement.setInt(4, entity.getHotel().getId());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, Room entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(6, entity.getId());
    }

    @Override
    public List<Room> findAll(Integer hotelId, Integer numberOnPage, Integer firstRoomOnPage, String sorting, String ordering) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(FIND_ALL_BY_HOTEL_ID_PARAMETER, sorting, ordering))) {
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setInt(2, firstRoomOnPage);
            preparedStatement.setInt(3, numberOnPage);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Room> entities = new HashSet<>();
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
    public Integer countAll(Integer hotelId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ALL_BY_HOTEL_ID)) {
            preparedStatement.setInt(1, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count_rooms");
        } catch (SQLException e) {
            throw new DataBaseRuntimeException(e);
        }
    }

}
