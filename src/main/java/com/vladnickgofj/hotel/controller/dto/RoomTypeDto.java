package com.vladnickgofj.hotel.controller.dto;

import java.util.Objects;

public class RoomTypeDto{
    private final int typeId;
    private final String typeName;

    private RoomTypeDto(Builder builder) {
        typeId = builder.typeId;
        typeName = builder.typeName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private int typeId;
        private String typeName;

        private Builder() {
        }

        public Builder typeId(int val) {
            typeId = val;
            return this;
        }

        public Builder typeName(String val) {
            typeName = val;
            return this;
        }

        public RoomTypeDto build() {
            return new RoomTypeDto(this);
        }
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomTypeDto that = (RoomTypeDto) o;
        return typeId == that.typeId && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName);
    }

    @Override
    public String toString() {
        return "RoomTypeDto{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
