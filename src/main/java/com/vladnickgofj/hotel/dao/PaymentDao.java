package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.dao.entity.Payment;

import java.util.List;

public interface PaymentDao extends CrudDao<Payment,Integer>{

    void addBookingPayment(BookingDto bookingServiceById);

    Payment findPaymentByBookingId(Integer bookingId);

}

