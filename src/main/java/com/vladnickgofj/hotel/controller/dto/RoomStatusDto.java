package com.vladnickgofj.hotel.controller.dto;

import com.vladnickgofj.hotel.dao.entity.StatusStatement;

import java.time.LocalDate;
import java.util.Objects;

public class RoomStatusDto {
    private final Integer statusId;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final RoomDtoResponse roomDtoResponse;
    private final StatusStatement statusStatement;

    private RoomStatusDto(Builder builder) {
        statusId = builder.statusId;
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
        roomDtoResponse = builder.roomDtoResponse;
        statusStatement = builder.statusStatement;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer statusId;
        private LocalDate dateStart;
        private LocalDate dateEnd;
        private RoomDtoResponse roomDtoResponse;
        private StatusStatement statusStatement;

        private Builder() {
        }

        public Builder statusId(Integer val) {
            statusId = val;
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

        public Builder roomDtoResponse(RoomDtoResponse val) {
            roomDtoResponse = val;
            return this;
        }

        public Builder statusStatement(StatusStatement val) {
            statusStatement = val;
            return this;
        }

        public RoomStatusDto build() {
            return new RoomStatusDto(this);
        }
    }

    public Integer getStatusId() {
        return statusId;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public RoomDtoResponse getRoomDtoResponse() {
        return roomDtoResponse;
    }

    public StatusStatement getStatusStatement() {
        return statusStatement;
    }

    public Integer getNumberOfNights(){
        return (int) (dateEnd.toEpochDay() - dateStart.toEpochDay());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomStatusDto that = (RoomStatusDto) o;
        return Objects.equals(statusId, that.statusId) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(roomDtoResponse, that.roomDtoResponse) && Objects.equals(statusStatement, that.statusStatement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, dateStart, dateEnd, roomDtoResponse, statusStatement);
    }

    @Override
    public String toString() {
        return "RoomStatusDto{" +
                "statusId=" + statusId +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", roomDtoResponse=" + roomDtoResponse +
                ", statusStatement=" + statusStatement +
                '}';
    }
}