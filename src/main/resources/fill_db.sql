USE hotel;
INSERT INTO role(name)
VALUES ('admin'),
       ('user');

INSERT INTO users (first_name, last_name, email, password, role_id)
VALUES ('Stepan', 'Hauser', 'stepan@mail.com', '1234', 1),
       ('Andrey', 'Mastykov', 'andrey@mail.com', '1234', 2),
       ('Sergey', 'Galushtenko', 'sergey@mail.com', '1234', 2),
       ('Yeugeniy', 'Litvinov', 'yevgeniy@mail.com', '1234', 2),
       ('Nikolay', 'Bokatov', 'nikolay@mail.com', '1234', 2);

INSERT INTO room_type (name)
VALUES ('suite'),
       ('de lux');

INSERT INTO room_status (name)
VALUES ('free'),
       ('busy');

INSERT INTO hotel (name)
VALUES ('free'),
       ('busy');

INSERT INTO room (type_id, number_of_beds, status_id, price, hotel_id)
VALUES (1, 1, 1, 100, 1),
       (1, 2, 1, 150, 1),
       (2, 1, 1, 200, 1),
       (2, 2, 1, 300, 1);

INSERT INTO bookings_status(name)
VALUES ('not paid'),
       ('paid');
INSERT INTO bookings(check_in, check_out, room_id, night, book_time, booking_status_id, user_id)
VALUES ('2022-01-10', '2022-01-15', 1, 5, '2022-01-02', 1, 2),
       ('2022-01-12', '2022-01-16', 2, 4, '2022-01-10', 1, 3),
       ('2022-01-09', '2022-01-12', 3, 3, '2022-01-05', 1, 4),
       ('2022-02-14', '2022-01-16', 4, 4, '2022-02-10', 1, 5);

