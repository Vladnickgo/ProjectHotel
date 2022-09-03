package com.vladnickgofj.hotel.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class PaymentDto {
    private final Integer userId;
    private final Integer roomId;
    private final LocalDate checkIn;
    private final LocalDate checkOut;

    private PaymentDto(Builder builder) {
        userId = builder.userId;
        roomId = builder.roomId;
        checkIn = builder.checkIn;
        checkOut = builder.checkOut;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer userId;
        private Integer roomId;
        private LocalDate checkIn;
        private LocalDate checkOut;

        private Builder() {
        }

        public Builder userId(Integer val) {
            userId = val;
            return this;
        }

        public Builder roomId(Integer val) {
            roomId = val;
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

        public PaymentDto build() {
            return new PaymentDto(this);
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDto that = (PaymentDto) o;
        return Objects.equals(userId, that.userId) && Objects.equals(roomId, that.roomId) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roomId, checkIn, checkOut);
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "userId=" + userId +
                ", roomId=" + roomId +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
