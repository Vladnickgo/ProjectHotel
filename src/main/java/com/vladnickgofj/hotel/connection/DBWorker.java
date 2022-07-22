package com.vladnickgofj.hotel.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    private static final Logger LOGGER = Logger.getLogger(DBWorker.class);
    private final String HOST = "jdbc:mysql://localhost:3306/hotel";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";

    private Connection connection;

    public DBWorker() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            LOGGER.info("Соединение установлено!");
        } catch (SQLException e) {
            LOGGER.info("Соединение не установлено!");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
