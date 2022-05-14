package com.vladnickgofj.hotel.dao.exception;

public class DataBaseRuntimeException extends RuntimeException{
    public DataBaseRuntimeException(String message) {
        super(message);
    }
}
