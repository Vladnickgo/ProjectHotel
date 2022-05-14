package com.vladnickgofj.hotel.dao.entity;

import java.util.Date;
import java.util.Objects;

public class Booking {
    private final int id;
    private final Date checkIn;
    private final Date checkOut;
    private final int roomId;
    private final int night;
    private final Date bookTime;
    private final int bookingStatusId;
    private final int usersId;

    private Booking(Builder builder) {
        id = builder.id;
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
        roomId = builder.roomId;
        night = builder.night;
        bookTime = builder.bookTime;
        bookingStatusId = builder.bookingStatusId;
        usersId = builder.usersId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public int getId() {
        return id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getNight() {
        return night;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public int getBookingStatusId() {
        return bookingStatusId;
    }

    public int getUsersId() {
        return usersId;
    }


    public static final class Builder {
        private int id;
        private Date checkIn;
        private Date checkOut;
        private int roomId;
        private int night;
        private Date bookTime;
        private int bookingStatusId;
        private int usersId;

        private Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder checkIn(Date val) {
            checkIn = val;
            return this;
        }

        public Builder checkOut(Date val) {
            checkOut = val;
            return this;
        }

        public Builder roomId(int val) {
            roomId = val;
            return this;
        }

        public Builder night(int val) {
            night = val;
            return this;
        }

        public Builder bookTime(Date val) {
            bookTime = val;
            return this;
        }

        public Builder bookingStatusId(int val) {
            bookingStatusId = val;
            return this;
        }

        public Builder usersId(int val) {
            usersId = val;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id
                && roomId == booking.roomId
                && night == booking.night
                && bookingStatusId == booking.bookingStatusId
                && usersId == booking.usersId
                && Objects.equals(checkIn, booking.checkIn)
                && Objects.equals(checkOut, booking.checkOut)
                && Objects.equals(bookTime, booking.bookTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkIn, checkOut, roomId, night, bookTime, bookingStatusId, usersId);
    }
}
