DROP DATABASE IF EXISTS testdb;
CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;
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
    salt       VARCHAR(255) NOT NULL,
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
    payment_id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    booking_id   INTEGER NOT NULL,
    user_id      INTEGER NOT NULL,
    amount       INTEGER NOT NULL,
    payment_date DATE    NOT NULL,
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

INSERT INTO role(name)
VALUES ('admin'),
       ('user');

INSERT INTO room_type (type_name)
VALUES ('Single'),
       ('Double'),
       ('Triple'),
       ('Quad'),
       ('Queen'),
       ('King'),
       ('Twin'),
       ('Double-double'),
       ('Suite'),
       ('President Suite');

INSERT INTO room_status_statement (status_statement_name)
VALUES ('free'),
       ('booked'),
       ('busy'),
       ('not available');

INSERT INTO booking_status(booking_status_name)
VALUES ('not paid'),
       ('paid'),
       ('user cancel'),
       ('auto cancel');

INSERT INTO users_order_status (order_status_name)
VALUES ('not done'),
       ('completed');

INSERT INTO hotel (hotel_name)
VALUES ('Eleon'),
       ('Kyiv');

INSERT INTO room (type_id, number_of_beds, price, hotel_id)
VALUES (1, 1, 700, 1),
       (1, 1, 600, 1),
       (2, 1, 1200, 1),
       (2, 2, 1100, 1),
       (1, 1, 600, 2),
       (2, 2, 1100, 2),
       (5, 2, 1200, 2);

INSERT INTO room_status(date_start, date_end, room_id, status_statement_id)
VALUES ('2022-08-01', '2022-11-01', 1, 1),
       ('2022-08-01', '2022-11-01', 2, 1),
       ('2022-08-01', '2022-11-01', 3, 1),
       ('2022-08-01', '2022-11-01', 4, 1),
       ('2022-08-01', '2022-11-01', 5, 1),
       ('2022-08-01', '2022-11-01', 6, 1),
       ('2022-08-01', '2022-11-01', 7, 1);

INSERT INTO users (first_name, last_name, email, password, salt, role_id)
VALUES ('Ivan', 'Bunin', 'admin@mail.com', 'fd06dd38d1fd964b21fbfa73c207cda337c45c3a', '7f1195dbf9222e9a', 1),
       ('Andrey', 'Mastykov', 'andrey@mail.com', '0a233ec67ca2b482e6d3e3c62941135d1b660199', 'e8afd5d44e2be783', 2),
       ('Sergey', 'Galushtenko', 'sergey@mail.com', '922e22af7f9762d93d314d2b9deea821f8666581', '420f28d49532f09a', 2),
       ('Gennadiy', 'Stolyarov', 'gena@mail.com', 'cd700eac16298b74fce0cab8de4051648e21578b', 'f69b200de4bbb082', 2);