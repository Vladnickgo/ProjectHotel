package com.vladnickgofj.hotel.dao.mapper;

import com.vladnickgofj.hotel.dao.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
                .checkIn(resultSet.getDate("check_in").toLocalDate())
                .checkOut(resultSet.getDate("check_out").toLocalDate())
                .room(mapResultSetToRoom(resultSet))
                .night(resultSet.getInt("night"))
                .bookTime(resultSet.getDate("book_time").toLocalDate())
                .bookingStatus(mapResultSetToBookingStatus(resultSet))
                .user(mapResultSetToUser(resultSet))
                .build();
    }

    public static Room mapResultSetToRoom(ResultSet resultSet) throws SQLException {
        return Room.newBuilder()
                .id(resultSet.getInt("room_id"))
                .roomType(mapResultSetToRoomType(resultSet))
                .numberOfBeds(resultSet.getInt("number_of_beds"))
//                .roomStatus(mapResultSetToRoomStatus(resultSet))
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
                .dateStart(resultSet.getDate("date_start").toLocalDate())
                .dateEnd(resultSet.getDate("date_end").toLocalDate())
                .room(mapResultSetToRoom(resultSet))
                .statusStatement(mapResultSetToStatusStatement(resultSet))
                .build();
    }

    private static StatusStatement mapResultSetToStatusStatement(ResultSet resultSet) throws SQLException{
        return StatusStatement.newBuilder()
                .statusStatementId(resultSet.getInt("status_statement_id"))
                .statusStatementName(resultSet.getString("status_statement_name"))
                .build();
    }

    public static RoomType mapResultSetToRoomType(ResultSet resultSet) throws SQLException {
        return RoomType.newBuilder()
                .id(resultSet.getInt("type_id"))
                .typeName(resultSet.getString("type_name"))
                .build();
    }

    public static UsersOrder mapResultSetToUsersOrder(ResultSet resultSet) throws SQLException{
        return UsersOrder.newBuilder()
                .id(resultSet.getInt("order_id"))
                .user(User.newBuilder()
                        .id(resultSet.getInt("user_id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .build())
                .hotel(Hotel.newBuilder()
                        .id(resultSet.getInt("hotel_id"))
                        .name(resultSet.getString("hotel_name"))
                        .build())
                .dateStart(resultSet.getDate("date_start").toLocalDate())
                .dateEnd(resultSet.getDate("date_end").toLocalDate())
                .orderDate(resultSet.getDate("order_date").toLocalDate())
                .numberOfPersons(resultSet.getInt("number_of_persons"))
                .room(Room.newBuilder()
                        .roomType(RoomType.newBuilder()
                                .id(resultSet.getInt("room_type_id"))
                                .typeName(resultSet.getString("type_name"))
                                .build())
                        .build())
                .orderStatus(OrderStatus.getOrderStatus(resultSet.getInt("order_status_id")))
                .build();
    }
}