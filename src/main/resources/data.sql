-- CAR
INSERT INTO car
VALUES (1, 'UNO', 'Fiat', '2010', 10000),
       (2, 'Toro', 'Fiat', '2021', 50000);

-- DRIVER
INSERT INTO driver
VALUES (1, '61387837010', '1999-04-01');

-- CUSTOMER
INSERT INTO customer
VALUES (1, 'Paulo Marcos de Andrade', 1);

-- CAR_DRIVER
INSERT INTO car_driver
VALUES (1, 1, 1, true),
       (2, 1, 2, true);
