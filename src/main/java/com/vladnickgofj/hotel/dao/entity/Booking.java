package com.vladnickgofj.hotel.dao.entity;

import java.util.Date;

public class Booking {
    private final int id;
    private final Date check_in;
    private final Date check_out;
    private final int roomId;
    private final int night;
    private final Date bookTime;
    private final int bookingStatusId;
    private final int usersId;

    public Booking(int id, Date check_in, Date check_out, int roomId, int night, Date bookTime, int bookingStatusId, int usersId) {
        this.id = id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.roomId = roomId;
        this.night = night;
        this.bookTime = bookTime;
        this.bookingStatusId = bookingStatusId;
        this.usersId = usersId;
    }
}
