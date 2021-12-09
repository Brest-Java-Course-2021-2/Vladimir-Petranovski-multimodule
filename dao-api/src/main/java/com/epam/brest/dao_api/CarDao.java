package com.epam.brest.dao_api;

import com.epam.brest.model.Car;

import java.util.List;

public interface CarDao {

    /**
     * Find all cars.
     *
     * @return list of cars.
     */

    List<Car> findAllCars();

    /**
     * Find car by Id.
     *
     * @param id car Id.
     * @return car.
     */

    Car findCarById(Integer id);

    /**
     * Persist new car.
     *
     * @param car car.
     * @return persisted car id.
     */

    Integer saveCar(Car car);

    /**
     * Update car.
     *
     * @param id car id.
     * @param car car.
     * @return number of updated records in the database.
     */

    Integer updateCarById(Integer id, Car car);

    /**
     * Delete car.
     *
     * @param id car id.
     * @return number of updated records in the database.
     */

    Integer deleteCarById(Integer id);

    /**
     * Count cars.
     *
     * @return quantity of the cars.
     */

    Integer count();
}
