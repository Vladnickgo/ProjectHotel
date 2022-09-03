package com.vladnickgofj.hotel.dao.entity;

import java.util.Objects;

public class RoomType {
    private final Integer id;
    private final String typeName;

    private RoomType(Builder builder) {
        id = builder.id;
        typeName = builder.typeName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String typeName;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder typeName(String val) {
            typeName = val;
            return this;
        }

        public RoomType build() {
            return new RoomType(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return Objects.equals(id, roomType.id) && Objects.equals(typeName, roomType.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
