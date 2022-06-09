package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.HotelDao;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.vladnickgofj.hotel.dao.mapper.ResultSetMapper.mapResultSetToHotel;

public class HotelDaoImpl extends AbstractCrudDaoImpl<Hotel> implements HotelDao {
    private final static String INSERT_INTO = "INSERT INTO hotel(name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM hotel WHERE hotel_id=?";
    private static final String FIND_ALL = "SELECT * FROM hotel";
    private static final String UPDATE_HOTEL = "UPDATE hotel SET name=? WHERE hotel_id=?";
    private static final String FIND_ALL_BY_PARAM = "SELECT * FROM hotel LIMIT ? OFFSET ?";
    private static final String COUNT_ALL = "SELECT COUNT(hotel_id) AS count_hotels FROM hotel";

    public HotelDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_HOTEL);
    }

    @Override
    protected Hotel mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return mapResultSetToHotel(resultSet);
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

    @Override
    public List<Hotel> findAll(Integer numberOfPage, Integer hotelsOnPage) {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_PARAM)) {
            preparedStatement.setInt(1, hotelsOnPage);
            preparedStatement.setInt(2, numberOfPage);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Hotel> entities = new HashSet<>();
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
    public Integer countAll() {
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ALL)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count_hotels");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
