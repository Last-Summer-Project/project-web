-- First user
INSERT INTO device(id, login_id, password)
VALUES (1, 'test_user', '$2a$10$pnLkZ2tz.k1ztO49vEUXTO38QNW5nfq9A5TzUYji8K9fq/ZmjTVDa');

-- Image
INSERT INTO image(id, name, url, date_created)
VALUES (1, 'image1', '/static/image/1.jpg', DATE_SUB(NOW(), INTERVAL 2 DAY));
INSERT INTO image(id, name, url, date_created)
VALUES (2, 'image2', '/static/image/2.jpg', DATE_SUB(NOW(), INTERVAL 1 DAY));
INSERT INTO image(id, name, url)
VALUES (3, 'image3', '/static/image/3.jpg');

-- DEVICE LOG
INSERT INTO device_log(id, device_id, temperature, relative_humidity, image_id, date_created)
VALUES (1, 1, 20.09, 30.00, 1, DATE_SUB(NOW(), INTERVAL 2 DAY));
INSERT INTO device_log(id, device_id, temperature, relative_humidity, image_id, date_created)
VALUES (2, 1, 20.09, 30.00, 2, DATE_SUB(NOW(), INTERVAL 1 DAY));
INSERT INTO device_log(id, device_id, temperature, relative_humidity, image_id)
VALUES (3, 1, 21.01, 35.55, 3);

-- DETECT
INSERT INTO detect(id, status, result)
VALUES (1, 'done', '0');
INSERT INTO detect(id, status, result)
VALUES (2, 'done', '2');
INSERT INTO detect(id, status, result)
VALUES (3, 'in_progress', NULL);

-- TIMELAPSE
INSERT INTO timelapse(id, device_id, start_date, end_date, status, result, date_created, last_edited)
VALUES (1, 1, '2023-05-20 00:00:00', '2023-05-30 00:00:00', 'done', 'result.mp4', DATE_SUB(NOW(), INTERVAL 1 HOUR), DATE_SUB(NOW(), INTERVAL 1 MINUTE));

-- For safety
DO SLEEP(1);