package com.vladnickgofj.hotel.service.mapper;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.dao.entity.*;

public class BookingMapper implements Mapper<BookingDto, Booking> {

    @Override
    public Booking mapDtoToEntity(BookingDto bookingDto) {
        return Booking.newBuilder()
                .id(bookingDto.getId())
                .checkIn(bookingDto.getCheckIn())
                .checkOut(bookingDto.getCheckOut())
                .room(Room.newBuilder()
                        .id(bookingDto.getRoom().getId())
                        .numberOfBeds(bookingDto.getRoom().getNumberOfBeds())
                        .roomType(RoomType.newBuilder()
                                .typeName(bookingDto.getRoom().getRoomType().getTypeName())
                                .build())
                        .price(bookingDto.getRoom().getPrice())
                        .hotel(Hotel.newBuilder()
                                .name(bookingDto.getRoom().getHotel().getName())
                                .build())
                        .build())
                .bookTime(bookingDto.getBookTime())
                .night(bookingDto.getNights())
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
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .room(Room.newBuilder()
                        .id(booking.getRoom().getId())
                        .numberOfBeds(booking.getRoom().getNumberOfBeds())
                        .hotel(Hotel.newBuilder()
                                .name(booking.getRoom().getHotel().getName())
                                .build())
                        .price(booking.getRoom().getPrice())
                        .roomType(RoomType.newBuilder()
                                .typeName(booking.getRoom().getRoomType().getTypeName())
                                .build())
                        .build())
                .bookTime(booking.getBookTime())
                .nights(booking.getNight())
                .bookingStatusId(booking.getBookingStatus().getId())
                .userId(booking.getUser().getId())
                .build();
    }
}
