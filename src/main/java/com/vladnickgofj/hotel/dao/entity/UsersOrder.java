package com.vladnickgofj.hotel.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class UsersOrder {
    private final Integer id;
    private final User user;
    private final Hotel hotel;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final LocalDate orderDate;
    private final Integer numberOfPersons;
    private final Room room;
    private final OrderStatus orderStatus;

    private UsersOrder(Builder builder) {
        id = builder.id;
        user = builder.user;
        hotel = builder.hotel;
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
        orderDate = builder.orderDate;
        numberOfPersons = builder.numberOfPersons;
        room = builder.room;
        orderStatus = builder.orderStatus;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private User user;
        private Hotel hotel;
        private LocalDate dateStart;
        private LocalDate dateEnd;
        private LocalDate orderDate;
        private Integer numberOfPersons;
        private Room room;
        private OrderStatus orderStatus;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder hotel(Hotel val) {
            hotel = val;
            return this;
        }

        public Builder dateStart(LocalDate val) {
            dateStart = val;
            return this;
        }

        public Builder dateEnd(LocalDate val) {
            dateEnd = val;
            return this;
        }

        public Builder orderDate(LocalDate val) {
            orderDate = val;
            return this;
        }

        public Builder numberOfPersons(Integer val) {
            numberOfPersons = val;
            return this;
        }

        public Builder room(Room val) {
            room = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public UsersOrder build() {
            return new UsersOrder(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public Room getRoom() {
        return room;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersOrder that = (UsersOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(hotel, that.hotel) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(orderDate, that.orderDate) && Objects.equals(numberOfPersons, that.numberOfPersons) && Objects.equals(room, that.room) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, hotel, dateStart, dateEnd, orderDate, numberOfPersons, room, orderStatus);
    }

    @Override
    public String toString() {
        return "UsersOrder{" +
                "id=" + id +
                ", user=" + user +
                ", hotel=" + hotel +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", orderDate=" + orderDate +
                ", numberOfPersons=" + numberOfPersons +
                ", room=" + room +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
