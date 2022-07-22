package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.BookingStatus;
import com.vladnickgofj.hotel.dao.entity.Room;
import com.vladnickgofj.hotel.dao.entity.User;

public class BookingMapper implements Mapper<BookingDto, Booking> {

    @Override
    public Booking mapDtoToEntity(BookingDto bookingDto) {
        return Booking.newBuilder()
                .id(bookingDto.getId())
                .checkIn(bookingDto.getCheck_in())
                .checkOut(bookingDto.getCheck_out())
                .room(Room.newBuilder()
                        .id(bookingDto.getRoomId())
                        .build())
                .bookTime(bookingDto.getBookTime())
                .bookingStatus(BookingStatus.newBuilder()
                        .id(bookingDto.getBookingStatusId())
                        .build())
                .user(User.newBuilder()
                        .id(bookingDto.getUserId())
                        .build())
                .build();
    }

    @Override
    public BookingDto mapEntityToDto(Booking booking) {
        return BookingDto.newBuilder()
                .id(booking.getId())
                .check_in(booking.getCheckIn())
                .check_out(booking.getCheckOut())
                .roomId(booking.getRoom().getId())
                .bookTime(booking.getBookTime())
                .bookingStatusId(booking.getBookingStatus().getId())
                .userId(booking.getUser().getId())
                .build();
    }
}
