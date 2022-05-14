package com.vladnickgofj.hotel.dao.impl;

import com.vladnickgofj.hotel.dao.UserDao;
import com.vladnickgofj.hotel.dao.entity.User;
import org.apache.catalina.UserDatabase;

import java.util.List;
import java.util.Optional;

public class UserDaoPostgressImpl implements UserDao {
    @Override
    public void save(User entity) {

    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
