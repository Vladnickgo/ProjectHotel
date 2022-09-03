package com.vladnickgofj.hotel.validator;

import com.vladnickgofj.hotel.controller.dto.BookingDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.*;

public class BookingValidator implements Validator<BookingDto> {

    @Override
    public void validate(BookingDto entity) {

        validateByParam(BookingDto::getCheckIn, NOT_CORRECT_CHECK_IN_DATE, entity);
        validateByParam(BookingDto::getCheckOut, NOT_CORRECT_CHECK_OUT_DATE, entity);
        validateByParam(BookingDto::getBookTime, NOT_CORRECT_DATE_OF_BOOKING, entity);

        notNullValidate(entity, BOOKING_IS_NULL_MESSAGE);
        notNullValidate(entity.getRoom(), DATA_ABOUT_ROOM_IS_ABSENT);
        notNullValidate(entity.getUserId(), USER_DATA_IS_ABSENT);

        datesValidate(BookingDto::getCheckIn, BookingDto::getCheckOut,
                (t, u) -> t.compareTo(u) >= 0, DATE_CHECK_IN_MORE_OR_EQUAL_THAN_DATE_CHECK_OUT, entity);
        datesValidate(BookingDto::getCheckIn, BookingDto::getBookTime,
                (t, u) -> t.compareTo(u) < 0, BOOKING_DATE_MORE_THAN_DATE_CHECK_IN, entity);
    }

    private void notNullValidate(Object object, String errorMessage) {
        Optional.ofNullable(object).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void validateByParam(Function<BookingDto, LocalDate> param, String errorMessage, BookingDto bookingDto) {
        Optional.ofNullable(param.apply(bookingDto)).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void datesValidate(Function<BookingDto, LocalDate> firstParam, Function<BookingDto, LocalDate> secondParam,
                               BiPredicate<LocalDate, LocalDate> predicate, String errorMessage, BookingDto bookingDto) {
        LocalDate firstDate = firstParam.apply(bookingDto);
        LocalDate secondDate = secondParam.apply(bookingDto);
        if (predicate.test(firstDate, secondDate)) throw new IllegalArgumentException(errorMessage);

    }


}
