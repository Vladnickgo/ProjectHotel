package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.BookingDto;
import com.vladnickgofj.hotel.controller.dto.PaymentDto;
import com.vladnickgofj.hotel.dao.BookingDao;
import com.vladnickgofj.hotel.dao.PaymentDao;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.Payment;
import com.vladnickgofj.hotel.service.PaymentService;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import org.apache.log4j.Logger;

public class PaymentServiceImpl implements PaymentService {
    private static final String BOOKING_WITH_ID_NOT_FOUND = "";
    private static final String BOOKING_WITH_ID_ALREADY_PAID = "";
    private static final String REGEX_FOR_CARD_NUMBER = "^([0-9]{16})";
    private static final String REGEX_FOR_CARD_CVV = "^([0-9]{3})";
    private final PaymentDao paymentDao;
    private final BookingDao bookingDao;
    //    private final BookingValidator bookingValidator;
    private final Mapper<PaymentDto, Payment> paymentMapper;

    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImpl.class);

    public PaymentServiceImpl(PaymentDao paymentDao, BookingDao bookingDao, Mapper<PaymentDto, Payment> paymentMapper) {
        this.paymentDao = paymentDao;
        this.bookingDao = bookingDao;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public void addPayment(BookingDto bookingServiceById) {
        Integer bookingStatusId = bookingServiceById.getBookingStatusId();
        Booking booking = bookingDao.findById(bookingStatusId).orElseThrow(() -> {
            return new IllegalArgumentException(String.format(BOOKING_WITH_ID_NOT_FOUND, bookingStatusId));
        });
        if (booking.getBookingStatus().equals("2"))
            throw new IllegalArgumentException(String.format(BOOKING_WITH_ID_ALREADY_PAID, bookingStatusId));

    }

    @Override
    public void checkCardData(String cardNumber, String cvvCode) {
        if (cardNumberValidation(cardNumber) || cvvValidation(cvvCode)) {
            LOGGER.info("Card number is not valid");
            throw new IllegalArgumentException("Card number is not valid");
        }
    }

    private boolean cvvValidation(String cvvCode) {
        return !cvvCode.matches(REGEX_FOR_CARD_CVV);
    }

    private boolean cardNumberValidation(String cardNumber) {
        return !cardNumber.matches(REGEX_FOR_CARD_NUMBER);
    }
}
