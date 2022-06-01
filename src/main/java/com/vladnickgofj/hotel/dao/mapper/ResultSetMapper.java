package com.vladnickgofj.hotel.dao.mapper;

import com.vladnickgofj.hotel.dao.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapper {

    public static Payment mapResultSetToPayment(ResultSet resultSet) throws SQLException {
        return Payment.newBuilder()
                .id(resultSet.getInt("payment_id"))
                .booking(mapResultSetToBooking(resultSet))
                .user(mapResultSetToUser(resultSet))
                .amount(resultSet.getInt("amount"))
                .build();
    }

    public static User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return User.newBuilder()
                .id(resultSet.getInt("user_id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(Role.getRole(resultSet.getInt("role_id")))
                .build();
    }

    public static Booking mapResultSetToBooking(ResultSet resultSet) throws SQLException {
        return Booking.newBuilder()
                .id(resultSet.getInt("booking_id"))
                .checkIn(resultSet.getDate("check_in"))
                .checkOut(resultSet.getDate("check_out"))
                .room(mapResultSetToRoom(resultSet))
                .night(resultSet.getInt("night"))
                .bookTime(resultSet.getDate("book_time"))
                .bookingStatus(mapResultSetToBookingStatus(resultSet))
                .user(mapResultSetToUser(resultSet))
                .build();
    }

    public static Room mapResultSetToRoom(ResultSet resultSet) throws SQLException {
        return Room.newBuilder()
                .id(resultSet.getInt("room_id"))
                .roomType(mapResultSetToRoomType(resultSet))
                .numberOfBeds(resultSet.getInt("number_of_beds"))
                .roomStatus(mapResultSetToRoomStatus(resultSet))
                .price(resultSet.getInt("price"))
                .hotel(mapResultSetToHotel(resultSet))
                .build();
    }

    public static Hotel mapResultSetToHotel(ResultSet resultSet) throws SQLException {
        return Hotel.newBuilder()
                .id(resultSet.getInt("hotel_id"))
                .name(resultSet.getString("hotel_name"))
                .build();
    }

    public static BookingStatus mapResultSetToBookingStatus(ResultSet resultSet) throws SQLException {
        return BookingStatus.newBuilder()
                .id(resultSet.getInt("booking_status_id"))
                .name(resultSet.getString("booking_status_name"))
                .build();
    }

    public static RoomStatus mapResultSetToRoomStatus(ResultSet resultSet) throws SQLException {
        return RoomStatus.newBuilder()
                .id(resultSet.getInt("status_id"))
                .name(resultSet.getString("status_name"))
                .build();
    }

    public static RoomType mapResultSetToRoomType(ResultSet resultSet) throws SQLException {
        return RoomType.newBuilder()
                .id(resultSet.getInt("type_id"))
                .typeName(resultSet.getString("type_name"))
                .build();
    }

}