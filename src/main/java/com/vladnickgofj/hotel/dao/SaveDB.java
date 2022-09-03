package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.connection.HikariConnectionPool;

public class SaveDB {
    private final HikariConnectionPool hikariConnectionPool=new HikariConnectionPool("bd-config");
}
