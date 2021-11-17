package com.epam.brest.dao_api;

import com.epam.brest.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> findAllCars();

    Car findCarById(Integer id);

    void saveCar(Car car);

    void updateCarById(Integer id, Car car);

    void deleteCarById(Integer id);
}
