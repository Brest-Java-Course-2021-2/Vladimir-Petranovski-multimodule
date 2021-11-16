package com.epam.brest.dao_api;

import com.epam.brest.model.Car;
import com.epam.brest.model.Driver;

import java.util.List;

public interface CarDao {

    List<Car> findAll();

    Driver findById(Integer id);

    void save(Car car);

    void update(Integer id, Car car);

    void delete(Integer id);
}
