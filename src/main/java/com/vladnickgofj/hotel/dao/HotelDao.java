package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.dao.entity.Hotel;

import java.util.List;

public interface HotelDao extends CrudDao<Hotel, Integer> {
    List<Hotel> findAll(Integer numberOfPage, Integer hotelsOnPage);
    Integer countAll();

}
