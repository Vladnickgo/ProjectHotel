package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.BookingRequestDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingDto getBookingByParameters(BookingDto bookingDto);

    BookingDto findById(Integer bookingId);

//    void addBookingPayment(BookingDto bookingServiceById);

    List<BookingDto> findBookingsByUserIdAndParameters(BookingRequestDto bookingRequestDto);

    Integer countAll(BookingRequestDto bookingDto);

    Integer getNumberOfPages(BookingRequestDto bookingRequestDto);

    Integer getNumberOfPage();

    void cancelBookingById(BookingDto byId, LocalDate dateEnd);

    void createNewBooking(BookingDto bookingDto, RoomStatusDto roomStatusDto);
}
