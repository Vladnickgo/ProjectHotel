package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.PaymentDto;
import com.vladnickgofj.hotel.dao.entity.Payment;

import java.time.LocalDate;

public class PaymentMapper implements Mapper<PaymentDto, Payment>{
    @Override
    public Payment mapDtoToEntity(PaymentDto paymentDto) {
        return null;
    }

    @Override
    public PaymentDto mapEntityToDto(Payment payment) {
        return PaymentDto.newBuilder()
                .userId(payment.getUser().getId())
                .roomId(payment.getBooking().getRoom().getId())
                .checkIn(payment.getBooking().getCheckIn())
                .checkOut(payment.getBooking().getCheckOut())
                .build();
    }
}
