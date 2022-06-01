USE hotel;
INSERT INTO role(name)
VALUES ('admin'),
       ('user');

INSERT INTO users (first_name, last_name, email, password, role_id)
VALUES ('Stepan', 'Hauser', 'stepan@mail.com', '1234', 1),
       ('Andrey', 'Mastykov', 'andrey@mail.com', '1234', 2),
       ('Sergey', 'Galushtenko', 'sergey@mail.com', '1234', 2),
       ('Yeugen', 'Litvinov', 'yevgeniy@mail.com', '1234', 2),
       ('Gennadiy', 'Stolyarov', 'gena@mail.com', '1234', 2),
       ('Sergey', 'Chuiko', 'chuiko@mail.com', '1234', 2),
       ('Ivan', 'Gnedenko', 'ivan@mail.com', '1234', 2),
       ('Artem', 'Fateev', 'artem@mail.com', '1234', 2);

INSERT INTO room_type (name)
VALUES ('suite'),
       ('de lux');

INSERT INTO room_status (name)
VALUES ('free'),
       ('busy');

INSERT INTO hotel (name)
VALUES ('Eleon'),
       ('Kyiv');

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
       ('2022-02-14', '2022-01-16', 4, 4, '2022-02-10', 1, 5),
       ('2022-01-10', '2022-01-15', 1, 5, '2022-01-02', 1, 3),
       ('2022-01-12', '2022-01-16', 2, 4, '2022-01-10', 1, 4),
       ('2022-01-09', '2022-01-12', 3, 3, '2022-01-05', 1, 2),
       ('2022-02-14', '2022-01-16', 4, 4, '2022-02-10', 1, 2);

insert into payments (booking_id, user_id, amount)
VALUES ((select booking_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 1),
        (select bookings.user_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 1),
        (select price
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 1)),

       ((select booking_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 2),
        (select bookings.user_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 2),
        (select price
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 2)),

       ((select booking_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 3),
        (select bookings.user_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 3),
        (select price
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 3)),

       ((select booking_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 4),
        (select bookings.user_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 4),
        (select price
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 4)),

       ((select booking_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 5),
        (select bookings.user_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 5),
        (select price
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 5)),

       ((select booking_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 6),
        (select bookings.user_id
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 6),
        (select price
         from bookings
                  left join users u on u.user_id = bookings.user_id
                  left join room r on r.room_id = bookings.room_id
         where bookings.booking_id = 6));
