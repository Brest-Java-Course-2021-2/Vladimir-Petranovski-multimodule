package com.epam.brest.dao_api;


import com.epam.brest.model.Driver;

import java.util.List;

public interface DriverDao {

    List<Driver> findAllDrivers();

    Driver findDriverById(Integer id);

    void saveDriver(Driver driver);

    void updateDriverById(Integer id, Driver driver);

    void deleteDriverById(Integer id);

    Integer count();
}
