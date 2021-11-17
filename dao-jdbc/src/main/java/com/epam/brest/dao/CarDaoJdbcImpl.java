package com.epam.brest.dao;

import com.epam.brest.dao_api.CarDao;
import com.epam.brest.dao_api.DriverDao;
import com.epam.brest.model.Car;

import java.util.List;

public class CarDaoJdbcImpl implements CarDao {

    @Override
    public List<Car> findAllCars() {
        return null;
    }

    @Override
    public Car findCarById(Integer id) {
        return null;
    }

    @Override
    public void saveCar(Car car) {

    }

    @Override
    public void updateCarById(Integer id, Car car) {

    }

    @Override
    public void deleteCarById(Integer id) {

    }
}
