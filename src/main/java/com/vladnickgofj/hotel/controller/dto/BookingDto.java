package com.vladnickgofj.hotel.controller.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BookingDto implements Serializable {
    private final Integer id;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final RoomDto room;
    private final Integer nights;
    private final LocalDate bookTime;
    private final Integer bookingStatusId;
    private final Integer userId;

    private BookingDto(Builder builder) {
        id = builder.id;
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
        room = builder.room;
        nights = builder.nights;
        bookTime = builder.bookTime;
        bookingStatusId = builder.bookingStatusId;
        userId = builder.userId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private RoomDto room;
        private Integer nights;
        private LocalDate bookTime;
        private Integer bookingStatusId;
        private Integer userId;

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

        public Builder room(RoomDto val) {
            room = val;
            return this;
        }

        public Builder nights(Integer val) {
            nights = val;
            return this;
        }

        public Builder bookTime(LocalDate val) {
            bookTime = val;
            return this;
        }

        public Builder bookingStatusId(Integer val) {
            bookingStatusId = val;
            return this;
        }

        public Builder userId(Integer val) {
            userId = val;
            return this;
        }

        public BookingDto build() {
            return new BookingDto(this);
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

    public RoomDto getRoom() {
        return room;
    }

    public Integer getNights() {
        return nights;
    }

    public LocalDate getBookTime() {
        return bookTime;
    }

    public Integer getBookingStatusId() {
        return bookingStatusId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDto that = (BookingDto) o;
        return Objects.equals(id, that.id) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut) && Objects.equals(room, that.room) && Objects.equals(nights, that.nights) && Objects.equals(bookTime, that.bookTime) && Objects.equals(bookingStatusId, that.bookingStatusId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkIn, checkOut, room, nights, bookTime, bookingStatusId, userId);
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "id=" + id +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", room=" + room +
                ", nights=" + nights +
                ", bookTime=" + bookTime +
                ", bookingStatusId=" + bookingStatusId +
                ", userId=" + userId +
                '}';
    }
}
