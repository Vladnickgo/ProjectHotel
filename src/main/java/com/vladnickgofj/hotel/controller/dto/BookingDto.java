package com.vladnickgofj.hotel.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class BookingDto {
    private final Integer id;
    private final LocalDate check_in;
    private final LocalDate check_out;
    private final Integer roomId;
    private final LocalDate bookTime;
    private final Integer bookingStatusId;
    private final Integer userId;

    private BookingDto(Builder builder) {
        id = builder.id;
        check_in = builder.check_in;
        check_out = builder.check_out;
        roomId = builder.roomId;
        bookTime = builder.bookTime;
        bookingStatusId = builder.bookingStatusId;
        userId = builder.userId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private LocalDate check_in;
        private LocalDate check_out;
        private Integer roomId;
        private LocalDate bookTime;
        private Integer bookingStatusId;
        private Integer userId;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder check_in(LocalDate val) {
            check_in = val;
            return this;
        }

        public Builder check_out(LocalDate val) {
            check_out = val;
            return this;
        }

        public Builder roomId(Integer val) {
            roomId = val;
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

    public LocalDate getCheck_in() {
        return check_in;
    }

    public LocalDate getCheck_out() {
        return check_out;
    }

    public Integer getRoomId() {
        return roomId;
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
        return Objects.equals(id, that.id) && Objects.equals(check_in, that.check_in) && Objects.equals(check_out, that.check_out) && Objects.equals(roomId, that.roomId) && Objects.equals(bookTime, that.bookTime) && Objects.equals(bookingStatusId, that.bookingStatusId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, check_in, check_out, roomId, bookTime, bookingStatusId, userId);
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "id=" + id +
                ", check_in=" + check_in +
                ", check_out=" + check_out +
                ", roomId=" + roomId +
                ", bookTime=" + bookTime +
                ", bookingStatusId=" + bookingStatusId +
                ", userId=" + userId +
                '}';
    }
}
