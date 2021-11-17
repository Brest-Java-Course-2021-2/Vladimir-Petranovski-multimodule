package com.epam.brest.service_api;

import com.epam.brest.model.Driver;

import java.util.List;

public interface DriverService {

    /**
     * Get list of driver Dao.
     *
     * @return list of driver Dao.
     */

    List<Driver> findAllDrivers();
}
