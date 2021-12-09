package com.epam.brest.dao_api;


import com.epam.brest.model.Driver;

import java.util.List;

public interface DriverDao {

    /**
     * Find all drivers.
     *
     * @return list of drivers.
     */

    List<Driver> findAllDrivers();


    /**
     * Find driver by Id.
     *
     * @param id driver Id.
     * @return driver.
     */

    Driver findDriverById(Integer id);

    /**
     * Persist new driver.
     *
     * @param driver driver.
     * @return persisted driver id.
     */

    Integer saveDriver(Driver driver);

    /**
     * Update department.
     *
     * @param id driver id.
     * @param driver driver.
     * @return number of updated records in the database.
     */

    Integer updateDriverById(Integer id, Driver driver);

    /**
     * Delete driver.
     *
     * @param id driver id.
     * @return number of updated records in the database.
     */

    Integer deleteDriverById(Integer id);

    /**
     * Count drivers.
     *
     * @return quantity of the drivers.
     */

    Integer count();
}
