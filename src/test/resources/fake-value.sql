-- First user
INSERT INTO device(id, login_id, password)
VALUES (1, 'test_user', '$2a$10$pnLkZ2tz.k1ztO49vEUXTO38QNW5nfq9A5TzUYji8K9fq/ZmjTVDa');

-- Image
INSERT INTO image(id, name, url)
VALUES (1, 'image1', '/static/image/1.jpg');
INSERT INTO image(id, name, url)
VALUES (2, 'image2', '/static/image/2.jpg');

-- DEVICE LOG
INSERT INTO device_log(id, device_id, temperature, relative_humidity, soil_humidity, image_id)
VALUES (1, 1, 20.09, 30.00, 0, 1);
INSERT INTO device_log(id, device_id, temperature, relative_humidity, soil_humidity, image_id)
VALUES (2, 1, 21.01, 35.55, 0, 2);

-- DETECT
INSERT INTO detect(id, status, result)
VALUES (1, 'done', '2');
INSERT INTO detect(id, status, result)
VALUES (2, 'in_progress', NULL);