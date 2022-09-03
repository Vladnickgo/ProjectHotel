package com.vladnickgofj.hotel.validator;

import com.vladnickgofj.hotel.controller.dto.RoomDto;

import java.util.Optional;
import java.util.function.Function;

public class RoomValidator implements Validator<RoomDto> {

    public static final String ROOM_DTO_IS_NULL_MESSAGE = "RoomDto is null";
    public static final String NOT_VALID_ROOM_TYPE_ID_MESSAGE = "Room type ID is not valid";
    public static final String NOT_VALID_NUMBER_OF_BEDS_MESSAGE = "Number of beds is not valid";
    public static final String NOT_VALID_PRICE_MESSAGE = "Price is not valid";
    public static final String NOT_VALID_HOTEL_ID_MESSAGE = "Hotel ID is not valid";

    @Override
    public void validate(RoomDto entity) {
        if (entity == null) throw new IllegalArgumentException(ROOM_DTO_IS_NULL_MESSAGE);
        validateByParam(RoomDto::getRoomTypeId, NOT_VALID_ROOM_TYPE_ID_MESSAGE, entity);
        validateByParam(RoomDto::getNumberOfBeds, NOT_VALID_NUMBER_OF_BEDS_MESSAGE, entity);
        validateByParam(RoomDto::getPrice, NOT_VALID_PRICE_MESSAGE, entity);
        validateByParam(RoomDto::getHotelId, NOT_VALID_HOTEL_ID_MESSAGE, entity);
    }

    private void validateByParam(Function<RoomDto, Integer> param, String errorMessage, RoomDto roomDto) {
        Optional.ofNullable(param.apply(roomDto)).filter(t -> t > 0).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
