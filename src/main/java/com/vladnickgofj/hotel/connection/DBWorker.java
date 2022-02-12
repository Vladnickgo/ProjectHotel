package com.vladnickgofj.hotel.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    private final String HOST = "jdbc:mysql://localhost:3306/hotel";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";

    private Connection connection;

    public DBWorker() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            System.out.println("Соединение установлено!");
        } catch (SQLException e) {
            System.out.println("Соединение не установлено!");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
