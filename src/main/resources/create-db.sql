DROP DATABASE IF EXISTS hotel;
CREATE DATABASE IF NOT EXISTS hotel;
USE hotel;
CREATE TABLE IF NOT EXISTS role
(
    role_id INTEGER PRIMARY KEY auto_increment,
    name    VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS users
(
    user_id    INTEGER PRIMARY KEY auto_increment,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role_id    INTEGER      NOT NULL,
    FOREIGN KEY (role_id) REFERENCES Role (role_id)
);
CREATE TABLE IF NOT EXISTS hotel
(
    hotel_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    hotel_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS room_type
(
    type_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS room
(
    room_id        INTEGER PRIMARY KEY auto_increment,
    type_id        INTEGER NOT NULL,
    number_of_beds INTEGER NOT NULL,
    price          INTEGER NOT NULL,
    hotel_id       INTEGER NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotel (hotel_id),
    FOREIGN KEY (type_id) REFERENCES Room_type (type_id)
);
CREATE TABLE IF NOT EXISTS booking_status
(
    booking_status_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    booking_status_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS bookings
(
    booking_id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    check_in          DATE    NOT NULL,
    check_out         DATE    NOT NULL,
    room_id           INTEGER NOT NULL,
    night             INTEGER AS (datediff(check_out, check_in)),
    book_time         DATE    NOT NULL,
    booking_status_id INTEGER NOT NULL,
    user_id           INTEGER NOT NULL,
    FOREIGN KEY (booking_status_id) REFERENCES booking_status (booking_status_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (room_id) REFERENCES room (room_id)
);

CREATE TABLE IF NOT EXISTS payments
(
    payment_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    booking_id INTEGER NOT NULL,
    user_id    INTEGER NOT NULL,
    amount     INTEGER NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES Bookings (booking_id)
);

CREATE TABLE IF NOT EXISTS room_status_statement
(
    status_statement_id   INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    status_statement_name VARCHAR(255)        NOT NULL
);

CREATE TABLE IF NOT EXISTS room_status
(
    status_id           INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    date_start          DATE    NOT NULL,
    date_end            DATE    NOT NULL,
    room_id             INTEGER NOT NULL,
    status_statement_id INTEGER NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room (room_id),
    FOREIGN KEY (status_statement_id) REFERENCES room_status_statement (status_statement_id)
);

CREATE TABLE IF NOT EXISTS users_order_status
(
    order_status_id   INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_status_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_order
(
    order_id          INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id           INTEGER NOT NULL,
    hotel_id          INTEGER NOT NULL,
    date_start        DATE    NOT NULL,
    date_end          DATE    NOT NULL,
    order_date        DATE    NOT NULL,
    number_of_persons INTEGER NOT NULL,
    room_type_id      INTEGER NOT NULL,
    order_status_id   INTEGER NOT NULL,
    FOREIGN KEY (order_status_id) REFERENCES users_order_status (order_status_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel (hotel_id),
    FOREIGN KEY (room_type_id) REFERENCES room_type (type_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);