package com.epam.brest.service_api;

import com.epam.brest.model.Car;

import java.util.List;

public interface CarService {

    /**
     * Get list of car.
     *
     * @return list of car.
     */

    List<Car> findAllCars();

    /**
     * Get car by id.
     *
     * @return car by id.
     */

    Car findCarById(Integer id);

    /**
     * Save car.
     */

    void saveCar(Car car);

    /**
     * Update car by id.
     */

    void updateCarById(Integer id, Car car);

    /**
     * Delete car by id.
     */

    void deleteCarById(Integer id);

    /**
     * Get count of records.
     *
     * @return count of records.
     */

    Integer count();
}
