package com.vladnickgofj.hotel.validator;

public interface Validator<E>{
    void validate(E entity);
}
