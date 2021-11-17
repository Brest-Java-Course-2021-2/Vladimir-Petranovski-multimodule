package com.epam.brest.dao_api;


import com.epam.brest.model.Driver;

import java.util.List;

public interface DriverDao {

    List<Driver> findAll();

    Driver findById(Integer id);

    void save(Driver driver);

    void update(Integer id, Driver driver);

    void delete(Integer id);
}
