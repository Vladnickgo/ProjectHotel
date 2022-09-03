package com.vladnickgofj.hotel.dao.entity;

import java.util.Objects;

public class StatusStatement {
    private final Integer statusStatementId;
    private final String statusStatementName;

    private StatusStatement(Builder builder) {
        statusStatementId = builder.statusStatementId;
        statusStatementName = builder.statusStatementName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer statusStatementId;
        private String statusStatementName;

        private Builder() {
        }

        public Builder statusStatementId(Integer val) {
            statusStatementId = val;
            return this;
        }

        public Builder statusStatementName(String val) {
            statusStatementName = val;
            return this;
        }

        public StatusStatement build() {
            return new StatusStatement(this);
        }
    }

    public Integer getStatusStatementId() {
        return statusStatementId;
    }

    public String getStatusStatementName() {
        return statusStatementName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusStatement that = (StatusStatement) o;
        return Objects.equals(statusStatementId, that.statusStatementId) && Objects.equals(statusStatementName, that.statusStatementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusStatementId, statusStatementName);
    }

    @Override
    public String toString() {
        return "StatusStatement{" +
                "statusStatementId=" + statusStatementId +
                ", statusStatementName='" + statusStatementName + '\'' +
                '}';
    }
}
