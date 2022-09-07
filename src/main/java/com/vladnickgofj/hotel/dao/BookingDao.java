package com.vladnickgofj.hotel.dao;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.BookingRequestDto;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;

import java.time.LocalDate;
import java.util.List;

public interface BookingDao extends CrudDao<Booking,Integer>{

    Booking findBookingByParameters(BookingDto bookingDto);

//    void addBookingPayment(BookingDto bookingServiceById);

    Integer countAll(BookingRequestDto bookingRequestDto);

    List<Booking> findBookingsByUserIdAndParameters(BookingRequestDto bookingRequestDto, Integer firstRecordOnPage);

    void cancelBookingById(BookingDto byId, LocalDate dateEnd);

    void createNewBooking(Booking booking, RoomStatus roomStatus);
}
