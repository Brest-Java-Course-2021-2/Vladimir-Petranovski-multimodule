package com.epam.brest.dao;

import org.springframework.stereotype.Component;

@Component
public class Queries {

    /**
     * Field constant DRIVER_FIND_ALL.
     */

    public static final String DRIVER_FIND_ALL = "SELECT * FROM driver";

    /**
     * Field constant DRIVER_FIND_BY_ID.
     */

    public static final String DRIVER_FIND_BY_ID =
            "SELECT * FROM driver WHERE driver_id=:driverId";

    /**
     * Field constant DRIVER_SAVE.
     */

    public static final String DRIVER_SAVE =
            "INSERT INTO driver (name, dateStartWork, salary)"
                    + " VALUES (:driverName, :driverDateStartWork, :driverSalary)";

    /**
     * Field constant DRIVER_UPDATE_BY_ID.
     */

    public static final String DRIVER_UPDATE_BY_ID =
            "UPDATE driver SET name=:driverName, dateStartWork=:driverDateStartWork,"
                    + " salary=:driverSalary WHERE driver_id=:driverId";

    /**
     * Field constant DRIVER_DELETE_BY_ID.
     */

    public static final String DRIVER_DELETE_BY_ID =
            "DELETE FROM driver WHERE driver_id=:driverId";

    /**
     * Field constant DRIVER_CHECK_UNIQUE_NAME.
     */

    public static final String DRIVER_CHECK_UNIQUE_NAME =
            "SELECT COUNT(d.name) FROM driver d WHERE lower(d.name)=lower(:driverName)";

    /**
     * Field constant DRIVER_COUNT.
     */

    public static final String DRIVER_COUNT = "SELECT COUNT(*) FROM driver";

    /**
     * Field constant DRIVER_COUNT_CAR.
     */

    public static final String DRIVER_COUNT_CAR =
            "SELECT d.driver_id as driverId, d.name as driverName,"
                    + " d.dateStartWork as driverDateStartWork, d.salary as driverSalary,"
                    + " COUNT(c.car_id) as countOfCarsAssignedToDriver FROM driver d LEFT JOIN"
                    + " car c ON d.driver_id=c.driver_id GROUP BY d.driver_id";

    /**
     * Field constant DRIVER_FIND_DRIVERS_ON_RANGE_DATE.
     */

    public static final String DRIVER_FIND_DRIVERS_ON_RANGE_DATE =
            "SELECT d.driver_id as driverId, d.name as driverName,"
                    + " d.dateStartWork as driverDateStartWork, d.salary as driverSalary"
                    + " FROM driver d WHERE dateStartWork BETWEEN"
                    + " :fromDateChoose AND :toDateChoose";

    /**
     * Field constant CAR_FIND_ALL.
     */

    public static final String CAR_FIND_ALL = "SELECT * FROM car";

    /**
     * Field constant CAR_FIND_BY_ID.
     */

    public static final String CAR_FIND_BY_ID =
            "SELECT * FROM car WHERE car_id=:carId";

    /**
     * Field constant CAR_SAVE.
     */

    public static final String CAR_SAVE =
            "INSERT INTO car (model, driver_id) VALUES (:carModel, :driverId)";

    /**
     * Field constant CAR_UPDATE_BY_ID.
     */

    public static final String CAR_UPDATE_BY_ID =
            "UPDATE car SET model=:carModel, driver_id=:driverId WHERE car_id=:carId";

    /**
     * Field constant CAR_DELETE_BY_ID.
     */

    public static final String CAR_DELETE_BY_ID =
            "DELETE FROM car WHERE car_id=:carId";

    /**
     * Field constant CAR_COUNT.
     */

    public static final String CAR_COUNT = "SELECT count(*) FROM car";
}
