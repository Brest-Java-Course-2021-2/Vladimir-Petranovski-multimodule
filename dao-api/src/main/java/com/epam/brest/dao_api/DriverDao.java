package com.epam.brest.dao_api;


import com.epam.brest.model.Driver;

import java.util.List;

public interface DriverDao {

    /**
     * Get list of driver.
     *
     * @return list of driver.
     */

    List<Driver> findAllDrivers();

    /**
     * Get driver by id.
     *
     * @return driver by id.
     */

    Driver findDriverById(Integer id);

    /**
     * Save driver.
     */

    Integer saveDriver(Driver driver);

    /**
     * Update driver by id.
     */

    Integer updateDriverById(Integer id, Driver driver);

    /**
     * Delete driver by id.
     */

    Integer deleteDriverById(Integer id);

    /**
     * Get count of records.
     *
     * @return count of records.
     */

    Integer count();
}
