package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.PaymentDto;
import com.vladnickgofj.hotel.dao.BookingDao;
import com.vladnickgofj.hotel.dao.PaymentDao;
import com.vladnickgofj.hotel.dao.entity.Payment;
import com.vladnickgofj.hotel.service.PaymentService;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @Mock
    private PaymentDao paymentDao;

    @Mock
    private BookingDao bookingDao;

    @Mock
    private Mapper<PaymentDto, Payment> mapper;

    @Test
    public void addPayment() {
    }

    @ParameterizedTest(name = "[{index}]{3}")
    @MethodSource("provideCreditCardData")
    public void checkNotValidCardData(String cardNumber, String cvvCode, String expectedMessage, String message) {
        PaymentService paymentService = new PaymentServiceImpl(paymentDao, bookingDao, mapper);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> paymentService.checkCardData(cardNumber, cvvCode));
        String actualMessage = illegalArgumentException.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    private static Stream<Arguments> provideCreditCardData() {
        return Stream.of(Arguments.of("", "", "Card number is not valid", "Card number and CVV is empty"),
                Arguments.of("1234567890", "123", "Card number is not valid", "Not valid card number"),
                Arguments.of("1234567812345678", "11", "Card number is not valid", "Not valid CVV value"),
                Arguments.of("123abc1212345678", "111", "Card number is not valid", "Not valid value of Card Number")
        );
    }


}