package com.vladnickgofj.hotel.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {
    private final Integer id;
    private final Booking booking;
    private final int amount;
    private final LocalDate paymentDate;

    private Payment(Builder builder) {
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
        private Booking booking;
        private int amount;
        private LocalDate paymentDate;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder booking(Booking val) {
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

        public Payment build() {
            return new Payment(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Booking getBooking() {
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
        Payment payment = (Payment) o;
        return amount == payment.amount && Objects.equals(id, payment.id) && Objects.equals(booking, payment.booking) && Objects.equals(paymentDate, payment.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, booking, amount, paymentDate);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", booking=" + booking +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
