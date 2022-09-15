DROP PROCEDURE IF EXISTS change_the_status_of_an_overdue_orders;
DROP PROCEDURE IF EXISTS change_date_end_for_room_status;
DELIMITER //
CREATE PROCEDURE change_the_status_of_an_overdue_orders()
BEGIN
    DECLARE booking_number INT;
    DECLARE booking_id_temp INT;
    DECLARE room_id_temp INT;
    DECLARE check_in_temp DATE;
    DECLARE check_out_temp DATE;
    DECLARE date_end_temp DATE;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION , SQLWARNING
        BEGIN
            ROLLBACK ;
        END;

    SELECT COUNT(*) INTO booking_number FROM bookings WHERE datediff(now(), book_time) > 2 AND booking_status_id = 1;
    WHILE booking_number > 0
        DO
            SELECT booking_id
            INTO booking_id_temp
            FROM bookings
            WHERE datediff(now(), book_time) > 2
              AND booking_status_id = 1
            LIMIT 0,1;

            SELECT room_id
            INTO room_id_temp
            FROM bookings
            WHERE datediff(now(), book_time) > 2
              AND booking_status_id = 1
            LIMIT 0,1;

            SELECT check_in
            INTO check_in_temp
            FROM bookings
            WHERE datediff(now(), book_time) > 2
              AND booking_status_id = 1
            LIMIT 0,1;

            SELECT check_out
            INTO check_out_temp
            FROM bookings
            WHERE datediff(now(), book_time) > 2
              AND booking_status_id = 1
            LIMIT 0,1;

            SELECT date_end
            INTO date_end_temp
            FROM room_status
            WHERE room_id = room_id_temp
              AND status_statement_id = 1
              AND date_start = check_out_temp;
            SET AUTOCOMMIT = 0;

            START TRANSACTION;
            UPDATE bookings SET booking_status_id=4 WHERE booking_id = booking_id_temp;

            UPDATE room_status
            SET date_end=date_end_temp
            WHERE room_id = room_id_temp
              AND status_statement_id = 1
              AND date_end = check_in_temp;

            DELETE
            FROM room_status
            WHERE room_id = room_id_temp
              AND date_start = check_in_temp
              AND date_end = check_out_temp
              AND status_statement_id = 2;

            DELETE
            FROM room_status
            WHERE room_id = room_id_temp
              AND date_start = check_out_temp
              AND status_statement_id = 1;
            COMMIT;

            SET booking_number = booking_number - 1;

        END WHILE;
END;
//
CREATE PROCEDURE change_date_end_for_room_status()
BEGIN
    DECLARE max_date_end DATE;
    DECLARE done INT default 0;
    DECLARE date_end_cursor CURSOR FOR SELECT max(date_end) FROM room_status GROUP BY room_id;
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
    OPEN date_end_cursor;
    WHILE done = 0
        DO
            FETCH date_end_cursor INTO max_date_end;
            UPDATE room_status
            SET date_end=DATE_ADD(now(), INTERVAL 6 MONTH)
            WHERE date_end = max_date_end
              AND status_statement_id = 1;
        END WHILE;
    CLOSE date_end_cursor;
END;
//
CALL change_date_end_for_room_status;
//
DROP EVENT IF EXISTS exec_change_the_status;
DROP EVENT IF EXISTS exec_change_date_end;
//
SET GLOBAL event_scheduler = ON;
CREATE EVENT exec_change_the_status
    ON SCHEDULE EVERY 24 HOUR
        STARTS '2022-08-10 12:00:00'
    ON COMPLETION NOT PRESERVE ENABLE
    DO
    CALL change_the_status_of_an_overdue_orders;
//
CREATE EVENT exec_change_date_end
    ON SCHEDULE EVERY 24 HOUR
        STARTS '2022-08-10 12:00:00'
    ON COMPLETION NOT PRESERVE ENABLE
    DO
    CALL change_date_end_for_room_status;
//
CALL change_the_status_of_an_overdue_orders;
CALL change_date_end_for_room_status;