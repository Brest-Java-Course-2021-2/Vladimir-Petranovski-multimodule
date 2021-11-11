package com.epam.brest.dao;

public class Queries {

    public static final String DRIVER_FIND_ALL = "SELECT * FROM driver";
    public static final String DRIVER_FIND_BY_ID = "SELECT * FROM driver WHERE id=?";
    public static final String DRIVER_SAVE = "INSERT INTO driver (name, dateStartWork, salary) VALUES (?1, ?2, ?3)";
    public static final String DRIVER_UPDATE_BY_ID = "UPDATE driver SET name=?2, dateStartWork=?3, salary=?4 WHERE id=?1";
    public static final String DRIVER_DELETE_BY_ID = "DELETE FROM driver WHERE id=?";
    public static final String DRIVER_COUNT_CAR = "SELECT COUNT(c.car_id) FROM driver d LEFT JOIN car c ON d.driver_id=c.driver_id GROUP BY d.driver_id";

    public static final String CAR_FIND_ALL = "SELECT * FROM car";
    public static final String CAR_FIND_BY_ID = "SELECT * FROM car WHERE id=?";
    public static final String CAR_SAVE ="INSERT INTO car (model, driver_id) VALUES (?1, ?2)";
    public static final String CAR_UPDATE_BY_ID = "UPDATE car SET model=?2, driver_id=?3 WHERE id=?1";
    public static final String CAR_DELETE_BY_ID = "DELETE FROM car WHERE id=?";
}
