package com.vladnickgofj.hotel.validator;

import com.vladnickgofj.hotel.controller.dto.UsersOrderDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.vladnickgofj.hotel.ApplicationConstant.MAX_NUMBER_OF_PERSONS_FOR_BOOKINGS;
import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.*;

public class UsersOrderValidator implements Validator<UsersOrderDto> {

    @Override
    public void validate(UsersOrderDto entity) {
        notNullValidate(entity, USERS_ORDER_IS_NULL);
        notNullValidate(entity.getUserDto(), USER_IS_NULL);
        notNullValidate(entity.getNumberOfPersons(), NUMBER_OF_PERSONS_IS_NULL);
        notNullValidate(entity.getHotelDto(), HOTEL_IS_NULL);
        notNullValidate(entity.getRoomDtoResponse(), ROOM_DTO_RESPONSE_IS_NULL);
        notNullValidate(entity.getOrderStatus(), ORDER_STATUS_IS_NULL);

        validateByParam(UsersOrderDto::getDateStart, NOT_CORRECT_CHECK_IN_DATE, entity);
        validateByParam(UsersOrderDto::getDateEnd, NOT_CORRECT_CHECK_OUT_DATE, entity);
        validateByParam(UsersOrderDto::getOrderDate, NOT_CORRECT_ORDER_DATE, entity);

        validateByIntParam(UsersOrderDto::getNumberOfPersons, t -> t > 0 && t <= MAX_NUMBER_OF_PERSONS_FOR_BOOKINGS, NOT_VALID_NUMBER_OF_PERSONS, entity);

        datesValidate(UsersOrderDto::getDateStart, UsersOrderDto::getDateEnd,
                (t, u) -> t.compareTo(u) >= 0, DATE_CHECK_IN_MORE_OR_EQUAL_THAN_DATE_CHECK_OUT, entity);
        datesValidate(UsersOrderDto::getDateStart, UsersOrderDto::getOrderDate,
                (t, u) -> t.compareTo(u) < 0, ORDER_DATE_MORE_THAN_DATE_CHECK_IN, entity);
    }

    private void validateByParam(Function<UsersOrderDto, LocalDate> param, String errorMessage, UsersOrderDto usersOrderDto) {
        Optional.ofNullable(param.apply(usersOrderDto)).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void datesValidate(Function<UsersOrderDto, LocalDate> firstParam, Function<UsersOrderDto, LocalDate> secondParam,
                               BiPredicate<LocalDate, LocalDate> predicate, String errorMessage, UsersOrderDto usersOrderDto) {
        LocalDate firstDate = firstParam.apply(usersOrderDto);
        LocalDate secondDate = secondParam.apply(usersOrderDto);
        if (predicate.test(firstDate, secondDate)) throw new IllegalArgumentException(errorMessage);

    }

    private void notNullValidate(Object object, String errorMessage) {
        Optional.ofNullable(object).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void validateByIntParam(Function<UsersOrderDto, Integer> param, Predicate<Integer> condition, String errorMessage, UsersOrderDto usersOrderDto) {
        Integer integerParam = param.apply(usersOrderDto);
        Optional.ofNullable(integerParam).filter(condition).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

}

