DROP DATABASE  hotel;
CREATE DATABASE IF NOT EXISTS hotel;
USE hotel;
CREATE TABLE IF NOT EXISTS role (
                                    role_id INTEGER PRIMARY KEY auto_increment,
                                    name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS users (
                                     user_id INTEGER PRIMARY KEY auto_increment,
                                     first_name VARCHAR(255) NOT NULL,
                                     last_name VARCHAR(255) NOT NULL,
                                     email VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     role_id INTEGER NOT NULL,
                                     FOREIGN KEY(role_id) REFERENCES Role(role_id)
);
CREATE TABLE IF NOT EXISTS hotel (
                                     hotel_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                     name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS room_status (
                                           status_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                           name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS room_type (
                                         type_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                         name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS room(
                                   room_id INTEGER PRIMARY KEY auto_increment,
                                   type_id INTEGER NOT NULL,
                                   number_of_beds INTEGER NOT NULL,
                                   status_id INTEGER NOT NULL,
                                   price INTEGER NOT NULL,
                                   hotel_id INTEGER NOT NULL,
                                   FOREIGN KEY(hotel_id) REFERENCES hotel(hotel_id),
                                   FOREIGN KEY(status_id) REFERENCES Room_status(status_id),
                                   FOREIGN KEY(type_id) REFERENCES Room_type(type_id)
);

CREATE TABLE IF NOT EXISTS bookings_status (
                                               booking_status_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                               name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings (
                                        booking_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                        check_in DATE NOT NULL,
                                        check_out DATE NOT NULL,
                                        room_id INTEGER NOT NULL,
                                        night INTEGER NOT NULL,
                                        book_time DATE NOT NULL,
                                        booking_status_id INTEGER NOT NULL,
                                        user_id INTEGER NOT NULL,
                                        FOREIGN KEY (booking_status_id) REFERENCES Bookings_status (booking_status_id),
                                        FOREIGN KEY (user_id) REFERENCES users (user_id),
                                        FOREIGN KEY (room_id) REFERENCES room(room_id)
);
CREATE TABLE IF NOT EXISTS payments (
                                        payment_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                                        booking_id INTEGER NOT NULL,
                                        user_id INTEGER NOT NULL,
                                        amount INTEGER NOT NULL,
                                        FOREIGN KEY (booking_id) REFERENCES Bookings (booking_id)
);
