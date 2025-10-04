CREATE TABLE IF NOT EXISTS car
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(20),
    make_year VARCHAR(4),
    fuel      VARCHAR(10),
    price     INT
);

INSERT INTO car (name, make_year, fuel, price)
VALUES ('Toyota', '2020', 'Petrol', 30000),
       ('Honda', '2019', 'Diesel', 25000),
       ('Ford', '2018', 'Electric', 27000);