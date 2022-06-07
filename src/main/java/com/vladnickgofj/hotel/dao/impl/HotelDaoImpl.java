package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.controller.dto.PaginateHotelDto;
import com.vladnickgofj.hotel.dao.HotelDao;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.dao.exception.DataBaseRuntimeException;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HotelDaoImpl extends AbstractCrudDaoImpl<Hotel> implements HotelDao {
    private final static String INSERT_INTO = "INSERT INTO hotel(name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM hotel WHERE hotel_id=?";
    private static final String FIND_ALL = "SELECT * FROM hotel";
    private static final String UPDATE_HOTEL = "UPDATE hotel SET name=? WHERE hotel_id=?";
    private static final String FIND_ALL_BY_PARAM = "SELECT * FROM hotel LIMIT ? OFFSET ?";

    public HotelDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_INTO, FIND_BY_ID, FIND_ALL, UPDATE_HOTEL);
    }

    @Override
    protected Hotel mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToHotel(resultSet);
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
            preparedStatement.setInt(1,hotelsOnPage);
            preparedStatement.setInt(2,numberOfPage);
            try (final ResultSet resultSet = preparedStatement.executeQuery();) {

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

    protected void mapForFindAllByParam(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 2);
    }

}
