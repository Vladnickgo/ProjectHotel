USE
    hotel;
INSERT INTO role(name)
VALUES ('admin'),
       ('user');

INSERT INTO users (first_name, last_name, email, password, role_id)
VALUES ('Vladimir', 'Golubkov', 'admin@mail.com', '1234', 1),
       ('Andrey', 'Mastykov', 'andrey@mail.com', '1234', 2),
       ('Sergey', 'Galushtenko', 'sergey@mail.com', '1234', 2),
       ('Yeugen', 'Litvinov', 'yevgeniy@mail.com', '1234', 2),
       ('Gennadiy', 'Stolyarov', 'gena@mail.com', '1234', 2),
       ('Sergey', 'Chuiko', 'chuiko@mail.com', '1234', 2),
       ('Ivan', 'Gnedenko', 'ivan@mail.com', '1234', 2),
       ('Artem', 'Fateev', 'artem@mail.com', '1234', 2),
       ('Sofiia', 'Holubkova', 'holubkova@test.com', '1234', 2),
       ('Inna', 'Golubkova', 'inna@gmail.com', '1234', 2),
       ('Aleksandr', 'Golubkov', 'algol@gmail.com', '123qwe', 2);

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
       ('canceled');

INSERT INTO users_order_status (order_status_name)
VALUES ('not done'),
       ('completed');

INSERT INTO hotel (hotel_name)
VALUES ('Eleon'),
       ('Kyiv'),
       ('Fazenda'),
       ('Vesela Bdzhilka'),
       ('Hotel Indigo'),
       ('Metropolis'),
       ('Dream'),
       ('Milan'),
       ('Sea Horizon'),
       ('Monet'),
       ('Hotel Emblem'),
       ('Na derevi'),
       ('Nota'),
       ('OK'),
       ('Pomaranchevyi rai'),
       ('Elephant Butte Inn'),
       ('Lime Wood'),
       ('Purple Orchid'),
       ('Pamir'),
       ('Pan Chemodan'),
       ('Papa Tour'),
       ('Птахи ta бдЖоли'),
       ('Radio'),
       ('Funky Monkey Lodge'),
       ('Rai'),
       ('Royal'),
       ('San City'),
       ('Llamas Pyjamas'),
       ('Silhouette'),
       ('Silver'),
       ('Бузковий'),
       ('Siti Star'),
       ('Skver'),
       ('Снігурі'),
       ('Sokil'),
       ('Sofia'),
       ('Standard'),
       ('Flagman'),
       ('Flamingo'),
       ('The Upper House'),
       ('Годинник'),
       ('Shovkova kurka'),
       ('Shelton'),
       ('Eden'),
       ('Sherlock'),
       ('EKONom'),
       ('Elite'),
       ('Estate');

INSERT INTO room (type_id, number_of_beds, price, hotel_id)
VALUES (1, 1, 700, 1),
       (1, 1, 600, 1),
       (2, 1, 1200, 1),
       (2, 2, 1100, 1),
       (1, 1, 800, 1),
       (1, 1, 650, 1),
       (2, 2, 1100, 1),
       (2, 2, 1100, 1),
       (3, 3, 1500, 1),
       (4, 4, 1800, 1),
       (5, 2, 1200, 1),
       (6, 2, 1300, 1),
       (8, 4, 1800, 1),
       (8, 4, 1800, 1),
       (10, 2, 50000, 1),
       (1, 1, 600, 2),
       (1, 1, 600, 2),
       (2, 2, 1100, 2),
       (4, 4, 1800, 2),
       (5, 2, 1200, 2),
       (7, 2, 1000, 2),
       (7, 2, 1000, 2),
       (8, 4, 1800, 2),
       (8, 4, 1800, 2),
       (9, 2, 10000, 2),
       (9, 2, 10000, 2),
       (9, 2, 10000, 2),
       (9, 2, 10000, 2),
       (9, 2, 10000, 2),
       (2, 2, 1100, 3),
       (4, 4, 1800, 3),
       (5, 2, 1200, 3),
       (5, 2, 1200, 3),
       (5, 2, 1200, 3),
       (5, 2, 1200, 3),
       (6, 2, 1300, 3),
       (6, 2, 1300, 3),
       (6, 2, 1300, 3),
       (7, 2, 1000, 3),
       (9, 2, 10000, 3),
       (9, 2, 10000, 3),
       (10, 2, 50000, 3),
       (1, 1, 600, 4),
       (1, 1, 600, 4),
       (2, 2, 1100, 4),
       (6, 2, 1300, 4),
       (6, 2, 1300, 4),
       (8, 4, 1800, 4),
       (9, 2, 10000, 4),
       (1, 1, 600, 5),
       (2, 2, 1100, 5),
       (2, 2, 1100, 5),
       (6, 2, 1300, 5),
       (7, 2, 1000, 5),
       (8, 4, 1800, 5),
       (9, 2, 10000, 5),
       (10, 2, 50000, 5),
       (5, 2, 1200, 6),
       (8, 4, 1800, 6),
       (9, 2, 10000, 6),
       (3, 3, 1500, 7),
       (5, 2, 1200, 7),
       (6, 2, 1300, 7),
       (7, 2, 1000, 7),
       (8, 4, 1800, 7),
       (9, 2, 10000, 7),
       (10, 2, 50000, 7),
       (2, 2, 1100, 8),
       (2, 2, 1100, 8),
       (2, 2, 1100, 8),
       (2, 2, 1100, 8),
       (3, 3, 1500, 8),
       (3, 3, 1500, 8),
       (4, 4, 1800, 8),
       (5, 2, 1200, 8),
       (6, 2, 1300, 8),
       (10, 2, 50000, 8),
       (2, 2, 1100, 9),
       (4, 4, 1800, 9),
       (6, 2, 1300, 9),
       (8, 4, 1800, 9),
       (10, 2, 50000, 9),
       (1, 1, 600, 10),
       (2, 2, 1100, 10),
       (3, 3, 1500, 10),
       (9, 2, 10000, 10),
       (9, 2, 10000, 10),
       (10, 2, 50000, 10),
       (2, 2, 1100, 11),
       (6, 2, 1300, 11),
       (7, 2, 1000, 11),
       (7, 2, 1000, 11),
       (7, 2, 1000, 11),
       (9, 2, 10000, 11),
       (10, 2, 50000, 11),
       (2, 2, 1100, 12),
       (3, 3, 1500, 12),
       (4, 4, 1800, 12),
       (4, 4, 1800, 12),
       (4, 4, 1800, 12),
       (4, 4, 1800, 12),
       (4, 4, 1800, 12),
       (5, 2, 1200, 12),
       (5, 2, 1200, 12),
       (5, 2, 1200, 12),
       (7, 2, 1000, 12),
       (3, 3, 1500, 13),
       (5, 2, 1200, 13),
       (6, 2, 1300, 13),
       (8, 4, 1800, 13),
       (2, 2, 1100, 14),
       (2, 2, 1100, 14),
       (3, 3, 1500, 14),
       (4, 4, 1800, 14),
       (6, 2, 1300, 14),
       (8, 4, 1800, 14),
       (8, 4, 1800, 14),
       (9, 2, 10000, 14),
       (2, 2, 1100, 15),
       (3, 3, 1500, 15),
       (3, 3, 1500, 15),
       (4, 4, 1800, 15),
       (4, 4, 1800, 15),
       (4, 4, 1800, 15),
       (6, 2, 1300, 15),
       (6, 2, 1300, 15),
       (6, 2, 1300, 15),
       (7, 2, 1000, 15),
       (8, 4, 1800, 15),
       (8, 4, 1800, 15),
       (8, 4, 1800, 15),
       (10, 2, 50000, 15),
       (10, 2, 50000, 15),
       (3, 3, 1500, 16),
       (3, 3, 1500, 16),
       (4, 4, 1800, 16),
       (4, 4, 1800, 16),
       (5, 2, 1200, 16),
       (6, 2, 1300, 16),
       (6, 2, 1300, 16),
       (8, 4, 1800, 16),
       (10, 2, 50000, 16),
       (10, 2, 50000, 16),
       (1, 1, 600, 17),
       (1, 1, 600, 17),
       (1, 1, 600, 17),
       (1, 1, 600, 17),
       (2, 2, 1100, 17),
       (6, 2, 1300, 17),
       (7, 2, 1000, 17),
       (8, 4, 1800, 17),
       (8, 4, 1800, 17),
       (10, 2, 50000, 17),
       (1, 1, 600, 18),
       (2, 2, 1100, 18),
       (2, 2, 1100, 18),
       (2, 2, 1100, 18),
       (3, 3, 1500, 18),
       (3, 3, 1500, 18),
       (4, 4, 1800, 18),
       (4, 4, 1800, 18),
       (7, 2, 1000, 18),
       (9, 2, 10000, 18),
       (1, 1, 600, 19),
       (2, 2, 1100, 19),
       (3, 3, 1500, 19),
       (4, 4, 1800, 19),
       (5, 2, 1200, 19),
       (5, 2, 1320, 19),
       (6, 2, 1300, 19),
       (6, 2, 1550, 19),
       (10, 2, 50000, 19),
       (1, 1, 600, 20),
       (1, 1, 600, 20),
       (1, 1, 600, 20),
       (2, 2, 1200, 20),
       (2, 2, 1350, 20),
       (3, 3, 1500, 20),
       (3, 3, 1500, 20),
       (7, 2, 1000, 20),
       (7, 2, 1000, 20),
       (8, 4, 1800, 20),
       (3, 3, 1450, 20),
       (3, 3, 1650, 20),
       (5, 2, 1480, 20),
       (1, 1, 600, 21),
       (2, 2, 1100, 21),
       (2, 2, 1100, 21),
       (2, 2, 1100, 21),
       (2, 2, 1100, 21),
       (2, 2, 1100, 21),
       (4, 4, 1800, 21),
       (3, 3, 1680, 21),
       (9, 2, 10000, 21),
       (10, 2, 50000, 21),
       (1, 1, 600, 22),
       (3, 3, 1500, 22),
       (3, 3, 1500, 22),
       (4, 4, 1800, 22),
       (5, 2, 1200, 22),
       (6, 2, 1300, 22),
       (8, 4, 1800, 22),
       (1, 1, 600, 23),
       (2, 2, 1100, 23),
       (4, 4, 1800, 23),
       (4, 4, 1800, 23),
       (5, 2, 1200, 23),
       (5, 2, 1200, 23),
       (6, 2, 1300, 23),
       (6, 2, 1300, 23),
       (7, 2, 1000, 23),
       (8, 4, 1800, 23),
       (9, 2, 10000, 23),
       (10, 2, 50000, 23),
       (1, 1, 600, 24),
       (4, 4, 1800, 24),
       (4, 4, 1800, 24),
       (9, 2, 10000, 24),
       (9, 2, 10000, 24),
       (10, 2, 50000, 24),
       (2, 2, 1100, 25),
       (4, 4, 1800, 25),
       (4, 4, 1800, 25),
       (5, 2, 1200, 25),
       (5, 2, 1200, 25),
       (5, 2, 1200, 25),
       (6, 2, 1300, 25),
       (7, 2, 1000, 25),
       (8, 4, 1800, 25),
       (8, 4, 1800, 25),
       (9, 2, 10000, 25),
       (1, 1, 600, 26),
       (1, 1, 600, 26),
       (2, 2, 1100, 26),
       (4, 4, 1800, 26),
       (7, 2, 1000, 26),
       (8, 4, 1800, 26),
       (10, 2, 50000, 26),
       (2, 2, 1100, 27),
       (3, 3, 1500, 27),
       (5, 2, 1200, 27),
       (7, 2, 1000, 27),
       (7, 2, 1000, 27),
       (7, 2, 1000, 27),
       (8, 4, 1800, 27),
       (9, 2, 10000, 27),
       (9, 2, 10000, 27),
       (10, 2, 50000, 27),
       (10, 2, 50000, 27),
       (1, 1, 600, 28),
       (3, 3, 1500, 28),
       (5, 2, 1200, 28),
       (1, 1, 600, 29),
       (1, 1, 600, 29),
       (1, 1, 600, 29),
       (3, 3, 1500, 29),
       (3, 3, 1500, 29),
       (4, 4, 1800, 29),
       (4, 4, 1800, 29),
       (7, 2, 1000, 29),
       (9, 2, 10000, 29),
       (10, 2, 50000, 29),
       (2, 2, 1100, 30),
       (2, 2, 1100, 30),
       (4, 4, 1800, 30),
       (5, 2, 1200, 30),
       (6, 2, 1300, 30),
       (6, 2, 1300, 30),
       (7, 2, 1000, 30),
       (9, 2, 10000, 30),
       (9, 2, 10000, 30),
       (9, 2, 10000, 30),
       (2, 2, 1100, 31),
       (2, 2, 1100, 31),
       (3, 3, 1500, 31),
       (4, 4, 1800, 31),
       (5, 2, 1200, 31),
       (6, 2, 1300, 31),
       (8, 4, 1800, 31),
       (9, 2, 10000, 31),
       (10, 2, 50000, 31),
       (10, 2, 50000, 31),
       (10, 2, 50000, 31),
       (10, 2, 50000, 31),
       (1, 1, 600, 32),
       (2, 2, 1100, 32),
       (3, 3, 1500, 32),
       (6, 2, 1300, 32),
       (7, 2, 1000, 32),
       (7, 2, 1000, 32),
       (8, 4, 1800, 32),
       (9, 2, 10000, 32),
       (10, 2, 50000, 32),
       (2, 2, 1100, 33),
       (3, 3, 1500, 33),
       (4, 4, 1800, 33),
       (7, 2, 1000, 33),
       (8, 4, 1800, 33),
       (9, 2, 10000, 33),
       (10, 2, 50000, 33),
       (1, 1, 600, 34),
       (1, 1, 600, 34),
       (2, 2, 1100, 34),
       (3, 3, 1500, 34),
       (4, 4, 1800, 34),
       (6, 2, 1300, 34),
       (7, 2, 1000, 34),
       (7, 2, 1000, 34),
       (7, 2, 1000, 34),
       (8, 4, 1800, 34),
       (9, 2, 5000, 34),
       (1, 1, 600, 35),
       (3, 3, 1500, 35),
       (3, 3, 1500, 35),
       (4, 4, 1750, 35),
       (4, 4, 1800, 35),
       (5, 2, 1200, 35),
       (5, 2, 1200, 35),
       (8, 4, 1800, 35),
       (10, 2, 15000, 35),
       (10, 2, 18000, 35),
       (2, 2, 1100, 36),
       (3, 3, 1500, 36),
       (3, 3, 1500, 36),
       (3, 3, 1500, 36),
       (5, 2, 1200, 36),
       (5, 2, 1200, 36),
       (6, 2, 1300, 36),
       (6, 2, 1300, 36),
       (8, 4, 1800, 36),
       (10, 2, 50000, 36),
       (2, 2, 1100, 37),
       (2, 2, 1100, 37),
       (5, 2, 1200, 37),
       (6, 2, 1300, 37),
       (6, 2, 1300, 37),
       (8, 4, 1800, 37),
       (9, 2, 10000, 37),
       (9, 2, 10000, 37),
       (1, 1, 600, 38),
       (2, 2, 1100, 38),
       (2, 2, 1100, 38),
       (3, 3, 1500, 38),
       (3, 3, 1500, 38),
       (5, 2, 1200, 38),
       (6, 2, 1300, 38),
       (7, 2, 1000, 38),
       (7, 2, 1000, 38),
       (8, 4, 1800, 38),
       (2, 2, 1100, 39),
       (4, 4, 1800, 39),
       (4, 4, 1800, 39),
       (4, 4, 1800, 39),
       (6, 2, 1300, 39),
       (7, 2, 1000, 39),
       (8, 4, 1800, 39),
       (8, 4, 1800, 39),
       (8, 4, 1800, 39),
       (9, 2, 10000, 39),
       (10, 2, 50000, 39),
       (10, 2, 50000, 39),
       (3, 3, 1500, 40),
       (3, 3, 1500, 40),
       (3, 3, 1500, 40),
       (4, 4, 1800, 40),
       (8, 4, 1800, 40),
       (10, 2, 50000, 40),
       (4, 4, 1800, 41),
       (5, 2, 1200, 41),
       (7, 2, 1000, 41),
       (7, 2, 1000, 41),
       (8, 4, 1800, 41),
       (8, 4, 1800, 41),
       (9, 2, 10000, 41),
       (10, 2, 50000, 41),
       (2, 2, 1100, 42),
       (3, 3, 1500, 42),
       (3, 3, 1500, 42),
       (4, 4, 1800, 42),
       (6, 2, 1300, 42),
       (6, 2, 1300, 42),
       (7, 2, 1000, 42),
       (8, 4, 1800, 42),
       (1, 1, 600, 43),
       (2, 2, 1100, 43),
       (2, 2, 1100, 43),
       (2, 2, 1100, 43),
       (4, 4, 1800, 43),
       (4, 4, 1800, 43),
       (7, 2, 1000, 43),
       (7, 2, 1000, 43),
       (9, 2, 10000, 43),
       (1, 1, 600, 44),
       (1, 1, 600, 44),
       (2, 2, 1100, 44),
       (5, 2, 1200, 44),
       (6, 2, 1300, 44),
       (7, 2, 1000, 44),
       (9, 2, 10000, 44),
       (10, 2, 50000, 44),
       (10, 2, 50000, 44),
       (2, 2, 1100, 45),
       (4, 4, 1800, 45),
       (6, 2, 1300, 45),
       (7, 2, 1000, 45),
       (9, 2, 10000, 45),
       (9, 2, 10000, 45),
       (10, 2, 50000, 45),
       (2, 2, 1100, 46),
       (2, 2, 1100, 46),
       (2, 2, 1100, 46),
       (3, 3, 1500, 46),
       (3, 3, 1500, 46),
       (7, 2, 1000, 46),
       (8, 4, 1800, 46),
       (9, 2, 10000, 46),
       (10, 2, 50000, 46),
       (1, 1, 600, 47),
       (1, 1, 600, 47),
       (2, 2, 1100, 47),
       (2, 2, 1100, 47),
       (3, 3, 1500, 47),
       (3, 3, 1500, 47),
       (5, 2, 1200, 47),
       (5, 2, 1200, 47),
       (8, 4, 1800, 47),
       (9, 2, 10000, 47),
       (9, 2, 10000, 47),
       (1, 1, 600, 48),
       (1, 1, 600, 48),
       (2, 2, 1100, 48),
       (2, 2, 1100, 48),
       (2, 2, 1100, 48),
       (2, 2, 1100, 48),
       (3, 3, 1500, 48),
       (4, 4, 1800, 48),
       (5, 2, 1200, 48),
       (5, 2, 1200, 48),
       (6, 2, 1300, 48),
       (7, 2, 1000, 48),
       (7, 2, 1000, 48);

INSERT INTO room_status(date_start, date_end, room_id, status_statement_id)
VALUES ('2022-08-01', '2022-11-01', 1, 1),
       ('2022-08-01', '2022-11-01', 2, 1),
       ('2022-08-01', '2022-11-01', 3, 1),
       ('2022-08-01', '2022-11-01', 4, 1),
       ('2022-08-01', '2022-11-01', 5, 1),
       ('2022-08-01', '2022-11-01', 6, 1),
       ('2022-08-01', '2022-11-01', 7, 1),
       ('2022-08-01', '2022-11-01', 8, 1),
       ('2022-08-01', '2022-11-01', 9, 1),
       ('2022-08-01', '2022-11-01', 10, 1),
       ('2022-08-01', '2022-11-01', 11, 1),
       ('2022-08-01', '2022-11-01', 12, 1),
       ('2022-08-01', '2022-11-01', 13, 1),
       ('2022-08-01', '2022-11-01', 14, 1),
       ('2022-08-01', '2022-11-01', 15, 1),
       ('2022-08-01', '2022-11-01', 16, 1),
       ('2022-08-01', '2022-11-01', 17, 1),
       ('2022-08-01', '2022-11-01', 18, 1),
       ('2022-08-01', '2022-11-01', 19, 1),
       ('2022-08-01', '2022-11-01', 20, 1),
       ('2022-08-01', '2022-11-01', 21, 1),
       ('2022-08-01', '2022-11-01', 22, 1),
       ('2022-08-01', '2022-11-01', 23, 1),
       ('2022-08-01', '2022-11-01', 24, 1),
       ('2022-08-01', '2022-11-01', 25, 1),
       ('2022-08-01', '2022-11-01', 26, 1),
       ('2022-08-01', '2022-11-01', 27, 1),
       ('2022-08-01', '2022-11-01', 28, 1),
       ('2022-08-01', '2022-11-01', 29, 1),
       ('2022-08-01', '2022-11-01', 30, 1),
       ('2022-08-01', '2022-11-01', 31, 1),
       ('2022-08-01', '2022-11-01', 32, 1),
       ('2022-08-01', '2022-11-01', 33, 1),
       ('2022-08-01', '2022-11-01', 34, 1),
       ('2022-08-01', '2022-11-01', 35, 1),
       ('2022-08-01', '2022-11-01', 36, 1),
       ('2022-08-01', '2022-11-01', 37, 1),
       ('2022-08-01', '2022-11-01', 38, 1),
       ('2022-08-01', '2022-11-01', 39, 1),
       ('2022-08-01', '2022-11-01', 40, 1),
       ('2022-08-01', '2022-11-01', 41, 1),
       ('2022-08-01', '2022-11-01', 42, 1),
       ('2022-08-01', '2022-11-01', 43, 1),
       ('2022-08-01', '2022-11-01', 44, 1),
       ('2022-08-01', '2022-11-01', 45, 1),
       ('2022-08-01', '2022-11-01', 46, 1),
       ('2022-08-01', '2022-11-01', 47, 1),
       ('2022-08-01', '2022-11-01', 48, 1),
       ('2022-08-01', '2022-11-01', 49, 1),
       ('2022-08-01', '2022-11-01', 50, 1),
       ('2022-08-01', '2022-11-01', 51, 1),
       ('2022-08-01', '2022-11-01', 52, 1),
       ('2022-08-01', '2022-11-01', 53, 1),
       ('2022-08-01', '2022-11-01', 54, 1),
       ('2022-08-01', '2022-11-01', 55, 1),
       ('2022-08-01', '2022-11-01', 56, 1),
       ('2022-08-01', '2022-11-01', 57, 1),
       ('2022-08-01', '2022-11-01', 58, 1),
       ('2022-08-01', '2022-11-01', 59, 1),
       ('2022-08-01', '2022-11-01', 60, 1),
       ('2022-08-01', '2022-11-01', 61, 1),
       ('2022-08-01', '2022-11-01', 62, 1),
       ('2022-08-01', '2022-11-01', 63, 1),
       ('2022-08-01', '2022-11-01', 64, 1),
       ('2022-08-01', '2022-11-01', 65, 1),
       ('2022-08-01', '2022-11-01', 66, 1),
       ('2022-08-01', '2022-11-01', 67, 1),
       ('2022-08-01', '2022-11-01', 68, 1),
       ('2022-08-01', '2022-11-01', 69, 1),
       ('2022-08-01', '2022-11-01', 70, 1),
       ('2022-08-01', '2022-11-01', 71, 1),
       ('2022-08-01', '2022-11-01', 72, 1),
       ('2022-08-01', '2022-11-01', 73, 1),
       ('2022-08-01', '2022-11-01', 74, 1),
       ('2022-08-01', '2022-11-01', 75, 1),
       ('2022-08-01', '2022-11-01', 76, 1),
       ('2022-08-01', '2022-11-01', 77, 1),
       ('2022-08-01', '2022-11-01', 78, 1),
       ('2022-08-01', '2022-11-01', 79, 1),
       ('2022-08-01', '2022-11-01', 80, 1),
       ('2022-08-01', '2022-11-01', 81, 1),
       ('2022-08-01', '2022-11-01', 82, 1),
       ('2022-08-01', '2022-11-01', 83, 1),
       ('2022-08-01', '2022-11-01', 84, 1),
       ('2022-08-01', '2022-11-01', 85, 1),
       ('2022-08-01', '2022-11-01', 86, 1),
       ('2022-08-01', '2022-11-01', 87, 1),
       ('2022-08-01', '2022-11-01', 88, 1),
       ('2022-08-01', '2022-11-01', 89, 1),
       ('2022-08-01', '2022-11-01', 90, 1),
       ('2022-08-01', '2022-11-01', 91, 1),
       ('2022-08-01', '2022-11-01', 92, 1),
       ('2022-08-01', '2022-11-01', 93, 1),
       ('2022-08-01', '2022-11-01', 94, 1),
       ('2022-08-01', '2022-11-01', 95, 1),
       ('2022-08-01', '2022-11-01', 96, 1),
       ('2022-08-01', '2022-11-01', 97, 1),
       ('2022-08-01', '2022-11-01', 98, 1),
       ('2022-08-01', '2022-11-01', 99, 1),
       ('2022-08-01', '2022-11-01', 100, 1),
       ('2022-08-01', '2022-11-01', 101, 1),
       ('2022-08-01', '2022-11-01', 102, 1),
       ('2022-08-01', '2022-11-01', 103, 1),
       ('2022-08-01', '2022-11-01', 104, 1),
       ('2022-08-01', '2022-11-01', 105, 1),
       ('2022-08-01', '2022-11-01', 106, 1),
       ('2022-08-01', '2022-11-01', 107, 1),
       ('2022-08-01', '2022-11-01', 108, 1),
       ('2022-08-01', '2022-11-01', 109, 1),
       ('2022-08-01', '2022-11-01', 110, 1),
       ('2022-08-01', '2022-11-01', 111, 1),
       ('2022-08-01', '2022-11-01', 112, 1),
       ('2022-08-01', '2022-11-01', 113, 1),
       ('2022-08-01', '2022-11-01', 114, 1),
       ('2022-08-01', '2022-11-01', 115, 1),
       ('2022-08-01', '2022-11-01', 116, 1),
       ('2022-08-01', '2022-11-01', 117, 1),
       ('2022-08-01', '2022-11-01', 118, 1),
       ('2022-08-01', '2022-11-01', 119, 1),
       ('2022-08-01', '2022-11-01', 120, 1),
       ('2022-08-01', '2022-11-01', 121, 1),
       ('2022-08-01', '2022-11-01', 122, 1),
       ('2022-08-01', '2022-11-01', 123, 1),
       ('2022-08-01', '2022-11-01', 124, 1),
       ('2022-08-01', '2022-11-01', 125, 1),
       ('2022-08-01', '2022-11-01', 126, 1),
       ('2022-08-01', '2022-11-01', 127, 1),
       ('2022-08-01', '2022-11-01', 128, 1),
       ('2022-08-01', '2022-11-01', 129, 1),
       ('2022-08-01', '2022-11-01', 130, 1),
       ('2022-08-01', '2022-11-01', 131, 1),
       ('2022-08-01', '2022-11-01', 132, 1),
       ('2022-08-01', '2022-11-01', 133, 1),
       ('2022-08-01', '2022-11-01', 134, 1),
       ('2022-08-01', '2022-11-01', 135, 1),
       ('2022-08-01', '2022-11-01', 136, 1),
       ('2022-08-01', '2022-11-01', 137, 1),
       ('2022-08-01', '2022-11-01', 138, 1),
       ('2022-08-01', '2022-11-01', 139, 1),
       ('2022-08-01', '2022-11-01', 140, 1),
       ('2022-08-01', '2022-11-01', 141, 1),
       ('2022-08-01', '2022-11-01', 142, 1),
       ('2022-08-01', '2022-11-01', 143, 1),
       ('2022-08-01', '2022-11-01', 144, 1),
       ('2022-08-01', '2022-11-01', 145, 1),
       ('2022-08-01', '2022-11-01', 146, 1),
       ('2022-08-01', '2022-11-01', 147, 1),
       ('2022-08-01', '2022-11-01', 148, 1),
       ('2022-08-01', '2022-11-01', 149, 1),
       ('2022-08-01', '2022-11-01', 150, 1),
       ('2022-08-01', '2022-11-01', 151, 1),
       ('2022-08-01', '2022-11-01', 152, 1),
       ('2022-08-01', '2022-11-01', 153, 1),
       ('2022-08-01', '2022-11-01', 154, 1),
       ('2022-08-01', '2022-11-01', 155, 1),
       ('2022-08-01', '2022-11-01', 156, 1),
       ('2022-08-01', '2022-11-01', 157, 1),
       ('2022-08-01', '2022-11-01', 158, 1),
       ('2022-08-01', '2022-11-01', 159, 1),
       ('2022-08-01', '2022-11-01', 160, 1),
       ('2022-08-01', '2022-11-01', 161, 1),
       ('2022-08-01', '2022-11-01', 162, 1),
       ('2022-08-01', '2022-11-01', 163, 1),
       ('2022-08-01', '2022-11-01', 164, 1),
       ('2022-08-01', '2022-11-01', 165, 1),
       ('2022-08-01', '2022-11-01', 166, 1),
       ('2022-08-01', '2022-11-01', 167, 1),
       ('2022-08-01', '2022-11-01', 168, 1),
       ('2022-08-01', '2022-11-01', 169, 1),
       ('2022-08-01', '2022-11-01', 170, 1),
       ('2022-08-01', '2022-11-01', 171, 1),
       ('2022-08-01', '2022-11-01', 172, 1),
       ('2022-08-01', '2022-11-01', 173, 1),
       ('2022-08-01', '2022-11-01', 174, 1),
       ('2022-08-01', '2022-11-01', 175, 1),
       ('2022-08-01', '2022-11-01', 176, 1),
       ('2022-08-01', '2022-11-01', 177, 1),
       ('2022-08-01', '2022-11-01', 178, 1),
       ('2022-08-01', '2022-11-01', 179, 1),
       ('2022-08-01', '2022-11-01', 180, 1),
       ('2022-08-01', '2022-11-01', 181, 1),
       ('2022-08-01', '2022-11-01', 182, 1),
       ('2022-08-01', '2022-11-01', 183, 1),
       ('2022-08-01', '2022-11-01', 184, 1),
       ('2022-08-01', '2022-11-01', 185, 1),
       ('2022-08-01', '2022-11-01', 186, 1),
       ('2022-08-01', '2022-11-01', 187, 1),
       ('2022-08-01', '2022-11-01', 188, 1),
       ('2022-08-01', '2022-11-01', 189, 1),
       ('2022-08-01', '2022-11-01', 190, 1),
       ('2022-08-01', '2022-11-01', 191, 1),
       ('2022-08-01', '2022-11-01', 192, 1),
       ('2022-08-01', '2022-11-01', 193, 1),
       ('2022-08-01', '2022-11-01', 194, 1),
       ('2022-08-01', '2022-11-01', 195, 1),
       ('2022-08-01', '2022-11-01', 196, 1),
       ('2022-08-01', '2022-11-01', 197, 1),
       ('2022-08-01', '2022-11-01', 198, 1),
       ('2022-08-01', '2022-11-01', 199, 1),
       ('2022-08-01', '2022-11-01', 200, 1),
       ('2022-08-01', '2022-11-01', 201, 1),
       ('2022-08-01', '2022-11-01', 202, 1),
       ('2022-08-01', '2022-11-01', 203, 1),
       ('2022-08-01', '2022-11-01', 204, 1),
       ('2022-08-01', '2022-11-01', 205, 1),
       ('2022-08-01', '2022-11-01', 206, 1),
       ('2022-08-01', '2022-11-01', 207, 1),
       ('2022-08-01', '2022-11-01', 208, 1),
       ('2022-08-01', '2022-11-01', 209, 1),
       ('2022-08-01', '2022-11-01', 210, 1),
       ('2022-08-01', '2022-11-01', 211, 1),
       ('2022-08-01', '2022-11-01', 212, 1),
       ('2022-08-01', '2022-11-01', 213, 1),
       ('2022-08-01', '2022-11-01', 214, 1),
       ('2022-08-01', '2022-11-01', 215, 1),
       ('2022-08-01', '2022-11-01', 216, 1),
       ('2022-08-01', '2022-11-01', 217, 1),
       ('2022-08-01', '2022-11-01', 218, 1),
       ('2022-08-01', '2022-11-01', 219, 1),
       ('2022-08-01', '2022-11-01', 220, 1),
       ('2022-08-01', '2022-11-01', 221, 1),
       ('2022-08-01', '2022-11-01', 222, 1),
       ('2022-08-01', '2022-11-01', 223, 1),
       ('2022-08-01', '2022-11-01', 224, 1),
       ('2022-08-01', '2022-11-01', 225, 1),
       ('2022-08-01', '2022-11-01', 226, 1),
       ('2022-08-01', '2022-11-01', 227, 1),
       ('2022-08-01', '2022-11-01', 228, 1),
       ('2022-08-01', '2022-11-01', 229, 1),
       ('2022-08-01', '2022-11-01', 230, 1),
       ('2022-08-01', '2022-11-01', 231, 1),
       ('2022-08-01', '2022-11-01', 232, 1),
       ('2022-08-01', '2022-11-01', 233, 1),
       ('2022-08-01', '2022-11-01', 234, 1),
       ('2022-08-01', '2022-11-01', 235, 1),
       ('2022-08-01', '2022-11-01', 236, 1),
       ('2022-08-01', '2022-11-01', 237, 1),
       ('2022-08-01', '2022-11-01', 238, 1),
       ('2022-08-01', '2022-11-01', 239, 1),
       ('2022-08-01', '2022-11-01', 240, 1),
       ('2022-08-01', '2022-11-01', 241, 1),
       ('2022-08-01', '2022-11-01', 242, 1),
       ('2022-08-01', '2022-11-01', 243, 1),
       ('2022-08-01', '2022-11-01', 244, 1),
       ('2022-08-01', '2022-11-01', 245, 1),
       ('2022-08-01', '2022-11-01', 246, 1),
       ('2022-08-01', '2022-11-01', 247, 1),
       ('2022-08-01', '2022-11-01', 248, 1),
       ('2022-08-01', '2022-11-01', 249, 1),
       ('2022-08-01', '2022-11-01', 250, 1),
       ('2022-08-01', '2022-11-01', 251, 1),
       ('2022-08-01', '2022-11-01', 252, 1),
       ('2022-08-01', '2022-11-01', 253, 1),
       ('2022-08-01', '2022-11-01', 254, 1),
       ('2022-08-01', '2022-11-01', 255, 1),
       ('2022-08-01', '2022-11-01', 256, 1),
       ('2022-08-01', '2022-11-01', 257, 1),
       ('2022-08-01', '2022-11-01', 258, 1),
       ('2022-08-01', '2022-11-01', 259, 1),
       ('2022-08-01', '2022-11-01', 260, 1),
       ('2022-08-01', '2022-11-01', 261, 1),
       ('2022-08-01', '2022-11-01', 262, 1),
       ('2022-08-01', '2022-11-01', 263, 1),
       ('2022-08-01', '2022-11-01', 264, 1),
       ('2022-08-01', '2022-11-01', 265, 1),
       ('2022-08-01', '2022-11-01', 266, 1),
       ('2022-08-01', '2022-11-01', 267, 1),
       ('2022-08-01', '2022-11-01', 268, 1),
       ('2022-08-01', '2022-11-01', 269, 1),
       ('2022-08-01', '2022-11-01', 270, 1),
       ('2022-08-01', '2022-11-01', 271, 1),
       ('2022-08-01', '2022-11-01', 272, 1),
       ('2022-08-01', '2022-11-01', 273, 1),
       ('2022-08-01', '2022-11-01', 274, 1),
       ('2022-08-01', '2022-11-01', 275, 1),
       ('2022-08-01', '2022-11-01', 276, 1),
       ('2022-08-01', '2022-11-01', 277, 1),
       ('2022-08-01', '2022-11-01', 278, 1),
       ('2022-08-01', '2022-11-01', 279, 1),
       ('2022-08-01', '2022-11-01', 280, 1),
       ('2022-08-01', '2022-11-01', 281, 1),
       ('2022-08-01', '2022-11-01', 282, 1),
       ('2022-08-01', '2022-11-01', 283, 1),
       ('2022-08-01', '2022-11-01', 284, 1),
       ('2022-08-01', '2022-11-01', 285, 1),
       ('2022-08-01', '2022-11-01', 286, 1),
       ('2022-08-01', '2022-11-01', 287, 1),
       ('2022-08-01', '2022-11-01', 288, 1),
       ('2022-08-01', '2022-11-01', 289, 1),
       ('2022-08-01', '2022-11-01', 290, 1),
       ('2022-08-01', '2022-11-01', 291, 1),
       ('2022-08-01', '2022-11-01', 292, 1),
       ('2022-08-01', '2022-11-01', 293, 1),
       ('2022-08-01', '2022-11-01', 294, 1),
       ('2022-08-01', '2022-11-01', 295, 1),
       ('2022-08-01', '2022-11-01', 296, 1),
       ('2022-08-01', '2022-11-01', 297, 1),
       ('2022-08-01', '2022-11-01', 298, 1),
       ('2022-08-01', '2022-11-01', 299, 1),
       ('2022-08-01', '2022-11-01', 300, 1),
       ('2022-08-01', '2022-11-01', 301, 1),
       ('2022-08-01', '2022-11-01', 302, 1),
       ('2022-08-01', '2022-11-01', 303, 1),
       ('2022-08-01', '2022-11-01', 304, 1),
       ('2022-08-01', '2022-11-01', 305, 1),
       ('2022-08-01', '2022-11-01', 306, 1),
       ('2022-08-01', '2022-11-01', 307, 1),
       ('2022-08-01', '2022-11-01', 308, 1),
       ('2022-08-01', '2022-11-01', 309, 1),
       ('2022-08-01', '2022-11-01', 310, 1),
       ('2022-08-01', '2022-11-01', 311, 1),
       ('2022-08-01', '2022-11-01', 312, 1),
       ('2022-08-01', '2022-11-01', 313, 1),
       ('2022-08-01', '2022-11-01', 314, 1),
       ('2022-08-01', '2022-11-01', 315, 1),
       ('2022-08-01', '2022-11-01', 316, 1),
       ('2022-08-01', '2022-11-01', 317, 1),
       ('2022-08-01', '2022-11-01', 318, 1),
       ('2022-08-01', '2022-11-01', 319, 1),
       ('2022-08-01', '2022-11-01', 320, 1),
       ('2022-08-01', '2022-11-01', 321, 1),
       ('2022-08-01', '2022-11-01', 322, 1),
       ('2022-08-01', '2022-11-01', 323, 1),
       ('2022-08-01', '2022-11-01', 324, 1),
       ('2022-08-01', '2022-11-01', 325, 1),
       ('2022-08-01', '2022-11-01', 326, 1),
       ('2022-08-01', '2022-11-01', 327, 1),
       ('2022-08-01', '2022-11-01', 328, 1),
       ('2022-08-01', '2022-11-01', 329, 1),
       ('2022-08-01', '2022-11-01', 330, 1),
       ('2022-08-01', '2022-11-01', 331, 1),
       ('2022-08-01', '2022-11-01', 332, 1),
       ('2022-08-01', '2022-11-01', 333, 1),
       ('2022-08-01', '2022-11-01', 334, 1),
       ('2022-08-01', '2022-11-01', 335, 1),
       ('2022-08-01', '2022-11-01', 336, 1),
       ('2022-08-01', '2022-11-01', 337, 1),
       ('2022-08-01', '2022-11-01', 338, 1),
       ('2022-08-01', '2022-11-01', 339, 1),
       ('2022-08-01', '2022-11-01', 340, 1),
       ('2022-08-01', '2022-11-01', 341, 1),
       ('2022-08-01', '2022-11-01', 342, 1),
       ('2022-08-01', '2022-11-01', 343, 1),
       ('2022-08-01', '2022-11-01', 344, 1),
       ('2022-08-01', '2022-11-01', 345, 1),
       ('2022-08-01', '2022-11-01', 346, 1),
       ('2022-08-01', '2022-11-01', 347, 1),
       ('2022-08-01', '2022-11-01', 348, 1),
       ('2022-08-01', '2022-11-01', 349, 1),
       ('2022-08-01', '2022-11-01', 350, 1),
       ('2022-08-01', '2022-11-01', 351, 1),
       ('2022-08-01', '2022-11-01', 352, 1),
       ('2022-08-01', '2022-11-01', 353, 1),
       ('2022-08-01', '2022-11-01', 354, 1),
       ('2022-08-01', '2022-11-01', 355, 1),
       ('2022-08-01', '2022-11-01', 356, 1),
       ('2022-08-01', '2022-11-01', 357, 1),
       ('2022-08-01', '2022-11-01', 358, 1),
       ('2022-08-01', '2022-11-01', 359, 1),
       ('2022-08-01', '2022-11-01', 360, 1),
       ('2022-08-01', '2022-11-01', 361, 1),
       ('2022-08-01', '2022-11-01', 362, 1),
       ('2022-08-01', '2022-11-01', 363, 1),
       ('2022-08-01', '2022-11-01', 364, 1),
       ('2022-08-01', '2022-11-01', 365, 1),
       ('2022-08-01', '2022-11-01', 366, 1),
       ('2022-08-01', '2022-11-01', 367, 1),
       ('2022-08-01', '2022-11-01', 368, 1),
       ('2022-08-01', '2022-11-01', 369, 1),
       ('2022-08-01', '2022-11-01', 370, 1),
       ('2022-08-01', '2022-11-01', 371, 1),
       ('2022-08-01', '2022-11-01', 372, 1),
       ('2022-08-01', '2022-11-01', 373, 1),
       ('2022-08-01', '2022-11-01', 374, 1),
       ('2022-08-01', '2022-11-01', 375, 1),
       ('2022-08-01', '2022-11-01', 376, 1),
       ('2022-08-01', '2022-11-01', 377, 1),
       ('2022-08-01', '2022-11-01', 378, 1),
       ('2022-08-01', '2022-11-01', 379, 1),
       ('2022-08-01', '2022-11-01', 380, 1),
       ('2022-08-01', '2022-11-01', 381, 1),
       ('2022-08-01', '2022-11-01', 382, 1),
       ('2022-08-01', '2022-11-01', 383, 1),
       ('2022-08-01', '2022-11-01', 384, 1),
       ('2022-08-01', '2022-11-01', 385, 1),
       ('2022-08-01', '2022-11-01', 386, 1),
       ('2022-08-01', '2022-11-01', 387, 1),
       ('2022-08-01', '2022-11-01', 388, 1),
       ('2022-08-01', '2022-11-01', 389, 1),
       ('2022-08-01', '2022-11-01', 390, 1),
       ('2022-08-01', '2022-11-01', 391, 1),
       ('2022-08-01', '2022-11-01', 392, 1),
       ('2022-08-01', '2022-11-01', 393, 1),
       ('2022-08-01', '2022-11-01', 394, 1),
       ('2022-08-01', '2022-11-01', 395, 1),
       ('2022-08-01', '2022-11-01', 396, 1),
       ('2022-08-01', '2022-11-01', 397, 1),
       ('2022-08-01', '2022-11-01', 398, 1),
       ('2022-08-01', '2022-11-01', 399, 1),
       ('2022-08-01', '2022-11-01', 400, 1),
       ('2022-08-01', '2022-11-01', 401, 1),
       ('2022-08-01', '2022-11-01', 402, 1),
       ('2022-08-01', '2022-11-01', 403, 1),
       ('2022-08-01', '2022-11-01', 404, 1),
       ('2022-08-01', '2022-11-01', 405, 1),
       ('2022-08-01', '2022-11-01', 406, 1),
       ('2022-08-01', '2022-11-01', 407, 1),
       ('2022-08-01', '2022-11-01', 408, 1),
       ('2022-08-01', '2022-11-01', 409, 1),
       ('2022-08-01', '2022-11-01', 410, 1),
       ('2022-08-01', '2022-11-01', 411, 1),
       ('2022-08-01', '2022-11-01', 412, 1),
       ('2022-08-01', '2022-11-01', 413, 1),
       ('2022-08-01', '2022-11-01', 414, 1),
       ('2022-08-01', '2022-11-01', 415, 1),
       ('2022-08-01', '2022-11-01', 416, 1),
       ('2022-08-01', '2022-11-01', 417, 1),
       ('2022-08-01', '2022-11-01', 418, 1),
       ('2022-08-01', '2022-11-01', 419, 1),
       ('2022-08-01', '2022-11-01', 420, 1),
       ('2022-08-01', '2022-11-01', 421, 1),
       ('2022-08-01', '2022-11-01', 422, 1),
       ('2022-08-01', '2022-11-01', 423, 1),
       ('2022-08-01', '2022-11-01', 424, 1),
       ('2022-08-01', '2022-11-01', 425, 1),
       ('2022-08-01', '2022-11-01', 426, 1),
       ('2022-08-01', '2022-11-01', 427, 1),
       ('2022-08-01', '2022-11-01', 428, 1),
       ('2022-08-01', '2022-11-01', 429, 1),
       ('2022-08-01', '2022-11-01', 430, 1),
       ('2022-08-01', '2022-11-01', 431, 1),
       ('2022-08-01', '2022-11-01', 432, 1),
       ('2022-08-01', '2022-11-01', 433, 1),
       ('2022-08-01', '2022-11-01', 434, 1),
       ('2022-08-01', '2022-11-01', 435, 1),
       ('2022-08-01', '2022-11-01', 436, 1),
       ('2022-08-01', '2022-11-01', 437, 1),
       ('2022-08-01', '2022-11-01', 438, 1),
       ('2022-08-01', '2022-11-01', 439, 1),
       ('2022-08-01', '2022-11-01', 440, 1);





