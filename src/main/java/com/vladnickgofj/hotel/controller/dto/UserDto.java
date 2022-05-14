package com.vladnickgofj.hotel.controller.dto;

import com.vladnickgofj.hotel.dao.entity.Role;

public class UserDto {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String confirmationPassword;
    private final Role role;

    public UserDto(int id, String firstName, String lastName, String email, String password, String confirmationPassword, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmationPassword='" + confirmationPassword + '\'' +
                ", role=" + role +
                '}';
    }
}
