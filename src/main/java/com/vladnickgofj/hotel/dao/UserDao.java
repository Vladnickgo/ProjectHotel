package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.dao.entity.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User,Integer>{
    Optional<User> findByEmail(String email);
}
