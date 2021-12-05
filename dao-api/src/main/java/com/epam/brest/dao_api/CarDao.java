package com.epam.brest.dao_api;

import com.epam.brest.model.Car;

import java.util.List;

public interface CarDao {

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

    Integer saveCar(Car car);

    /**
     * Update car by id.
     */

    Integer updateCarById(Integer id, Car car);

    /**
     * Delete car by id.
     */

    Integer deleteCarById(Integer id);

    /**
     * Get count of records.
     *
     * @return count of records.
     */

    Integer count();
}
