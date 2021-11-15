DROP TABLE IF EXISTS driver;
CREATE TABLE driver (
    driver_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE,
    dateStartWork TIMESTAMP NOT NULL,
    salary DECIMAL NOT NULL
);
DROP TABLE IF EXISTS car;
CREATE TABLE car (
    car_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(20) NOT NULL UNIQUE,
    driver_id INT NOT NULL
);