package com.vladnickgofj.hotel.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class RoomStatus {
    private final Integer id;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final Room room;
    private final StatusStatement statusStatement;

    private RoomStatus(Builder builder) {
        id = builder.id;
        dateStart = builder.dateStart;
        dateEnd = builder.dateEnd;
        room = builder.room;
        statusStatement = builder.statusStatement;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private LocalDate dateStart;
        private LocalDate dateEnd;
        private Room room;
        private StatusStatement statusStatement;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
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

        public Builder room(Room val) {
            room = val;
            return this;
        }

        public Builder statusStatement(StatusStatement val) {
            statusStatement = val;
            return this;
        }

        public RoomStatus build() {
            return new RoomStatus(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public Room getRoom() {
        return room;
    }

    public StatusStatement getStatusStatement() {
        return statusStatement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomStatus that = (RoomStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(room, that.room) && Objects.equals(statusStatement, that.statusStatement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateStart, dateEnd, room, statusStatement);
    }

    @Override
    public String toString() {
        return "RoomStatus{" +
                "id=" + id +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", room=" + room +
                ", statusStatement=" + statusStatement +
                '}';
    }
}


