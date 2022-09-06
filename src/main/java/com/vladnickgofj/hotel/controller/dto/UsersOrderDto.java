package com.vladnickgofj.hotel.controller.dto;

import com.vladnickgofj.hotel.dao.entity.OrderStatus;

import java.time.LocalDate;
import java.util.Objects;

public class UsersOrderDto {
    private final Integer id;
    private final UserDto userDto;
    private final HotelDto hotelDto;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final LocalDate orderDate;
    private final Integer numberOfPersons;
    private final RoomDtoResponse roomDtoResponse;
    private final OrderStatus orderStatus;

    private UsersOrderDto(Builder builder) {
        id = builder.id;
        userDto = builder.userDto;
        hotelDto = builder.hotelDto;
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
        orderDate = builder.orderDate;
        numberOfPersons = builder.numberOfPersons;
        roomDtoResponse = builder.roomDtoResponse;
        orderStatus = builder.orderStatus;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private UserDto userDto;
        private HotelDto hotelDto;
        private LocalDate dateStart;
        private LocalDate dateEnd;
        private LocalDate orderDate;
        private Integer numberOfPersons;
        private RoomDtoResponse roomDtoResponse;
        private OrderStatus orderStatus;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder userDto(UserDto val) {
            userDto = val;
            return this;
        }

        public Builder hotelDto(HotelDto val) {
            hotelDto = val;
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

        public Builder roomDtoResponse(RoomDtoResponse val) {
            roomDtoResponse = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public UsersOrderDto build() {
            return new UsersOrderDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public HotelDto getHotelDto() {
        return hotelDto;
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

    public RoomDtoResponse getRoomDtoResponse() {
        return roomDtoResponse;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersOrderDto that = (UsersOrderDto) o;
        return Objects.equals(id, that.id) && Objects.equals(userDto.getId(), that.userDto.getId()) && Objects.equals(hotelDto, that.hotelDto) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(orderDate, that.orderDate) && Objects.equals(numberOfPersons, that.numberOfPersons) && Objects.equals(roomDtoResponse, that.roomDtoResponse) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userDto.getId(), hotelDto, dateStart, dateEnd, orderDate, numberOfPersons, roomDtoResponse, orderStatus);
    }

    @Override
    public String toString() {
        return "UsersOrderDto{" +
                "id=" + id +
                ", userDto=" + userDto +
                ", hotelDto=" + hotelDto +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", orderDate=" + orderDate +
                ", numberOfPersons=" + numberOfPersons +
                ", roomDtoResponse=" + roomDtoResponse +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
