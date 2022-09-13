package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.entity.*;

public class PaymentMapper implements Mapper<PaymentDto, Payment> {
    @Override
    public Payment mapDtoToEntity(PaymentDto paymentDto) {

        return Payment.newBuilder()
                .id(paymentDto.getId())
                .booking(Booking.newBuilder()
                        .id(paymentDto.getBooking().getId())
                        .checkIn(paymentDto.getBooking().getCheckIn())
                        .checkOut(paymentDto.getBooking().getCheckOut())
                        .room(Room.newBuilder()
                                .id(paymentDto.getBooking().getRoom().getId())
                                .roomType(RoomType.newBuilder()
                                        .id(paymentDto.getBooking().getRoom().getRoomType().getTypeId())
                                        .typeName(paymentDto.getBooking().getRoom().getRoomType().getTypeName())
                                        .build())
                                .numberOfBeds(paymentDto.getBooking().getRoom().getNumberOfBeds())
                                .price(paymentDto.getBooking().getRoom().getPrice())
                                .hotel(Hotel.newBuilder()
                                        .id(paymentDto.getBooking().getRoom().getHotel().getId())
                                        .name(paymentDto.getBooking().getRoom().getHotel().getName())
                                        .build())
                                .build())
                        .night(paymentDto.getBooking().getNights())
                        .bookTime(paymentDto.getBooking().getBookTime())
                        .bookingStatus(BookingStatus.newBuilder()
                                .id(paymentDto.getBooking().getBookingStatusId())
                                .build())
                        .user(User.newBuilder()
                                .id(paymentDto.getBooking().getUserId())
                                .build())
                        .build())
                .build();
    }

    @Override
    public PaymentDto mapEntityToDto(Payment payment) {
        return PaymentDto.newBuilder()
                .id(payment.getId())
                .booking(BookingDto.newBuilder()
                        .id(payment.getBooking().getId())
                        .checkIn(payment.getBooking().getCheckIn())
                        .checkOut(payment.getBooking().getCheckOut())
                        .room(RoomDto.newBuilder()
                                .id(payment.getBooking().getRoom().getId())
                                .roomType(RoomTypeDto.newBuilder()
                                        .typeId(payment.getBooking().getRoom().getRoomType().getId())
                                        .typeName(payment.getBooking().getRoom().getRoomType().getTypeName())
                                        .build())
                                .numberOfBeds(payment.getBooking().getRoom().getNumberOfBeds())
                                .price(payment.getBooking().getRoom().getPrice())
                                .hotel(HotelDto.newBuilder()
                                        .id(payment.getBooking().getRoom().getHotel().getId())
                                        .name(payment.getBooking().getRoom().getHotel().getName())
                                        .build())
                                .build())
                        .nights(payment.getBooking().getNight())
                        .bookTime(payment.getBooking().getBookTime())
                        .bookingStatusId(payment.getBooking().getBookingStatus().getId())
                        .userId(payment.getBooking().getUser().getId())
                        .build())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
