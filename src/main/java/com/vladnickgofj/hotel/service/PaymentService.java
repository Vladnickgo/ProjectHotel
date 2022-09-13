package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.BookingRequestDto;
import com.vladnickgofj.hotel.controller.dto.PaymentDto;

public interface PaymentService {
    void addPayment(BookingDto bookingServiceById);

    void checkCardData(String cardNumber, String cvvCode);

    PaymentDto findPaymentByBookingId(Integer bookingId);

}
