package com.vladnickgofj.hotel.validator;

import com.vladnickgofj.hotel.controller.dto.HotelDto;

import java.util.Optional;

public class HotelValidator implements Validator<HotelDto>{
    @Override
    public void validate(HotelDto entity) {
       notNullValidate(entity,"Hotel is null");
       notNullValidate(entity.getName(),"Hotel name is null");
    }
    private void notNullValidate(Object object, String errorMessage) {
        Optional.ofNullable(object).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }
}
