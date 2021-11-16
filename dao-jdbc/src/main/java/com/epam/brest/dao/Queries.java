package com.epam.brest.dao;

public class Queries {

    public static final String DRIVER_FIND_ALL = "SELECT * FROM driver";
    public static final String DRIVER_FIND_BY_ID = "SELECT * FROM driver WHERE driver_id=:driverId";
    public static final String DRIVER_SAVE = "INSERT INTO driver (name, dateStartWork, salary) VALUES (:driverName, :driverDateStartWork, :driverSalary)";
    public static final String DRIVER_UPDATE_BY_ID = "UPDATE driver SET name=:driverName, dateStartWork=:driverDateStartWork, salary=:driverSalary WHERE driver_id=:driverId";
    public static final String DRIVER_DELETE_BY_ID = "DELETE FROM driver WHERE driver_id=:driverId";
    public static final String DRIVER_FIND_NAME_BY_ID = "SELECT name FROM driver WHERE driver_id=:driverId";
    public static final String DRIVER_FIND_ALL_NAME = "SELECT name FROM driver";
    public static final String DRIVER_COUNT_CAR = "SELECT d.driver_id, d.name, d.dateStartWork, d.salary, COUNT(c.car_id) as countOfCarsAssignedToDriver FROM driver d LEFT JOIN car c ON d.driver_id=c.driver_id GROUP BY d.driver_id";

    public static final String CAR_FIND_ALL = "SELECT * FROM car";
    public static final String CAR_FIND_BY_ID = "SELECT * FROM car WHERE car_id=:car_id";
    public static final String CAR_SAVE ="INSERT INTO car (model, driver_id) VALUES (:model, :driver_id)";
    public static final String CAR_UPDATE_BY_ID = "UPDATE car SET model=:model, driver_id=:driver_id WHERE car_id=:car_id";
    public static final String CAR_DELETE_BY_ID = "DELETE FROM car WHERE car_id=:car_id";
}
