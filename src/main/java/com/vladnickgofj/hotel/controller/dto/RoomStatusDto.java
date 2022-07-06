package com.vladnickgofj.hotel.controller.dto;

import java.util.Objects;

public class RoomStatusDto {
    private final Integer statusId;
    private final String statusName;

    private RoomStatusDto(Builder builder) {
        statusId = builder.statusId;
        statusName = builder.statusName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer statusId;
        private String statusName;

        private Builder() {
        }

        public Builder statusId(Integer val) {
            statusId = val;
            return this;
        }

        public Builder statusName(String val) {
            statusName = val;
            return this;
        }

        public RoomStatusDto build() {
            return new RoomStatusDto(this);
        }
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomStatusDto that = (RoomStatusDto) o;
        return Objects.equals(statusId, that.statusId) && Objects.equals(statusName, that.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusName);
    }

    @Override
    public String toString() {
        return "RoomStatusDto{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
