package com.vladnickgofj.hotel.dao.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private final Integer id;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final Room room;
    private final int night;
    private final LocalDate bookTime;
    private final BookingStatus bookingStatus;
    private final User user;

    private Booking(Builder builder) {
        id = builder.id;
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
        room = builder.room;
        night = builder.night;
        bookTime = builder.bookTime;
        bookingStatus = builder.bookingStatus;
        user = builder.user;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private Room room;
        private int night;
        private LocalDate bookTime;
        private BookingStatus bookingStatus;
        private User user;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder checkIn(LocalDate val) {
            checkIn = val;
            return this;
        }

        public Builder checkOut(LocalDate val) {
            checkOut = val;
            return this;
        }

        public Builder room(Room val) {
            room = val;
            return this;
        }

        public Builder night(int val) {
            night = val;
            return this;
        }

        public Builder bookTime(LocalDate val) {
            bookTime = val;
            return this;
        }

        public Builder bookingStatus(BookingStatus val) {
            bookingStatus = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Room getRoom() {
        return room;
    }

    public int getNight() {
        return night;
    }

    public LocalDate getBookTime() {
        return bookTime;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return night == booking.night && Objects.equals(id, booking.id) && Objects.equals(checkIn, booking.checkIn) && Objects.equals(checkOut, booking.checkOut) && Objects.equals(room, booking.room) && Objects.equals(bookTime, booking.bookTime) && Objects.equals(bookingStatus, booking.bookingStatus) && Objects.equals(user, booking.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkIn, checkOut, room, night, bookTime, bookingStatus, user);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", room=" + room +
                ", night=" + night +
                ", bookTime=" + bookTime +
                ", bookingStatus=" + bookingStatus +
                ", user=" + user +
                '}';
    }
}
