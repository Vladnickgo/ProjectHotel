package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.dao.entity.Payment;

public interface PaymentDao extends CrudDao<Payment,Integer>{

    void ddBookingPayment(BookingDto bookingServiceById);
}

