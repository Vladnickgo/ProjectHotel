package com.vladnickgofj.hotel.dao.entity;

import java.util.Objects;

public class Payment {
    private final Integer id;
    private final Booking booking;
    private final User user;
    private final int amount;

    private Payment(Builder builder) {
        id = builder.id;
        booking = builder.booking;
        user = builder.user;
        amount = builder.amount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private Booking booking;
        private User user;
        private int amount;

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

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder amount(int val) {
            amount = val;
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

    public User getUser() {
        return user;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return amount == payment.amount && Objects.equals(id, payment.id) && Objects.equals(booking, payment.booking) && Objects.equals(user, payment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, booking, user, amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", booking=" + booking +
                ", user=" + user +
                ", amount=" + amount +
                '}';
    }
}
