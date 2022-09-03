package com.vladnickgofj.hotel.controller.dto;

import com.vladnickgofj.hotel.PagesConstant;

import java.time.LocalDate;
import java.util.Objects;

public class LocalDateDto {
    private final String signIn;
    private final String signOut;

    private LocalDateDto(Builder builder) {
        signIn = builder.signIn;
        signOut = builder.signOut;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String signIn;
        private String signOut;

        private Builder() {
        }

        public Builder signIn(String val) {
            signIn = val;
            return this;
        }

        public Builder signOut(String val) {
            signOut = val;
            return this;
        }

        public LocalDateDto build() {
            return new LocalDateDto(this);
        }
    }

    public LocalDate getSignIn() {
        return LocalDate.parse(signIn == null ? String.valueOf(LocalDate.now()) : signIn);
    }

    public LocalDate getMinSignIn() {
        return LocalDate.now();
    }

    public LocalDate getMaxSignIn(){
        return LocalDate.now().plusMonths(1);
    }

    public LocalDate getSignOut() {
        return LocalDate.parse(signOut == null ? String.valueOf(LocalDate.now().plusDays(1)) : signOut);
    }

    public LocalDate getMinSignOut(){
        return getSignIn().plusDays(1);
    }

    public LocalDate getMaxSignOut(){
        return getMaxSignIn().plusDays(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalDateDto that = (LocalDateDto) o;
        return Objects.equals(signIn, that.signIn) && Objects.equals(signOut, that.signOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(signIn, signOut);
    }

    @Override
    public String toString() {
        return "LocalDateDto{" +
                "signIn='" + signIn + '\'' +
                ", signOut='" + signOut + '\'' +
                '}';
    }
}
