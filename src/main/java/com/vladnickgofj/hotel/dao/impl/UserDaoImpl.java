package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;
import com.vladnickgofj.hotel.dao.entity.User;
import com.vladnickgofj.hotel.dao.mapper.ResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractCrudDaoImpl<User> implements com.vladnickgofj.hotel.dao.UserDao {

    private static final String INSERT_QUERY = "INSERT INTO users(first_name, last_name, email, password, role_id) "+
            "VALUES(?,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE user_id=?";
    private static final String UPDATE_USER = "UPDATE users "+
            "SET first_name=?, last_name=?, email=?, password=?, role_id=? WHERE user_id=?";
    //    private static final String DELETE_USER = "DELETE FROM users WHERE user_id=?";
    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final int DEFAULT_USER_ROLE_ID = 2;

    public UserDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_QUERY, FIND_BY_ID, FIND_ALL, UPDATE_USER);
    }

    @Override
    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ResultSetMapper.mapResultSetToUser(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setString(4, entity.getPassword());
        preparedStatement.setInt(5, DEFAULT_USER_ROLE_ID);
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, User entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(6, entity.getId());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return findByStringParam(email, FIND_BY_EMAIL);
    }
}
