package com.vladnickgofj.hotel.dao.repository;

import com.vladnickgofj.hotel.connection.DBWorker;
import com.vladnickgofj.hotel.dao.entity.Role;
import com.vladnickgofj.hotel.dao.entity.User;

import java.sql.*;

public class UserRepository {
    private static final String INSERT_NEW = "INSERT INTO users(first_name, last_name, email, password, role_id) VALUES(?,?,?,?,?);";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE user_id";
    private static final String UPDATE_USER = "UPDATE users SET first_name=?, last_name=?, email=?, password=?, role_id=? WHERE user_id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id=?";
    public static final int DEFAULT_USER_ROLE_ID = 2;

    public User save(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DBWorker dbWorker = new DBWorker();
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = dbWorker.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, 2);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findById(int id) {
        Statement statement = null;
        DBWorker dbWorker = new DBWorker();
        Connection connection = dbWorker.getConnection();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_BY_ID);
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("user_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        Role.getRole(resultSet.getInt("role_id")));
                System.out.println(user.toString());
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error of reading from DB...");
        }
        System.out.println("user_id:" + id + " is not exist...");
        return null;
    }

    public void update(User user) {
        int id = user.getId();
        PreparedStatement preparedStatement = getPrepareStatement(UPDATE_USER);
        try {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, DEFAULT_USER_ROLE_ID);
            preparedStatement.setInt(6, id);
            preparedStatement.execute();
            System.out.println(user.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean delete(int id) {
        PreparedStatement preparedStatement = getPrepareStatement(DELETE_USER);
        try {
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public PreparedStatement getPrepareStatement(String stringQuery) {
        DBWorker dbWorker = new DBWorker();
        Connection connection = dbWorker.getConnection();
        try {
            return connection.prepareStatement(stringQuery);
        } catch (SQLException e) {
            return null;
        }
    }
}
