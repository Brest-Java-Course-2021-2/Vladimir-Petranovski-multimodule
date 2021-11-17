package com.epam.brest.dao;

import com.epam.brest.dao_api.CarDao;
import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Car;

import java.util.List;

public class CarDriverDaoJdbcImpl implements CarDao {

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car findById(Integer id) {
        return null;
    }

    @Override
    public void save(Car car) {

    }

    @Override
    public void update(Integer id, Car car) {

    }

    @Override
    public void delete(Integer id) {

    }
}
