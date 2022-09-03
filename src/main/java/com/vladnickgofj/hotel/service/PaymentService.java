package com.vladnickgofj.hotel.service;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.BookingRequestDto;

public interface PaymentService {
    void addPayment(BookingDto bookingServiceById);

    void checkCardData(String cardNumber, String cvvCode);
}
