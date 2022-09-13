package com.vladnickgofj.hotel.controller.dto;

import java.time.LocalDate;
import java.util.Objects;

public class PaymentDto {
    private final Integer id;
    private final BookingDto booking;
    private final int amount;
    private final LocalDate paymentDate;

    private PaymentDto(Builder builder) {
        id = builder.id;
        booking = builder.booking;
        amount = builder.amount;
        paymentDate = builder.paymentDate;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private BookingDto booking;
        private int amount;
        private LocalDate paymentDate;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder booking(BookingDto val) {
            booking = val;
            return this;
        }

        public Builder amount(int val) {
            amount = val;
            return this;
        }

        public Builder paymentDate(LocalDate val) {
            paymentDate = val;
            return this;
        }

        public PaymentDto build() {
            return new PaymentDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public BookingDto getBooking() {
        return booking;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDto that = (PaymentDto) o;
        return amount == that.amount && Objects.equals(id, that.id) && Objects.equals(booking, that.booking) && Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, booking, amount, paymentDate);
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "id=" + id +
                ", booking=" + booking +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
