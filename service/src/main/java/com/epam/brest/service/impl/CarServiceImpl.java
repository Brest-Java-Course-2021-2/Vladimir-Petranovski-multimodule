package com.epam.brest.service.impl;

import com.epam.brest.dao_api.CarDao;
import com.epam.brest.model.Car;
import com.epam.brest.service.exception.CarNotFoundException;
import com.epam.brest.service_api.CarService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.LOG;

@Service
public class CarServiceImpl implements CarService {

    /**
     * Field carDao.
     */

    private final CarDao carDao;

    /**
     * Constructor.
     *
     * @param carDao carDao.
     */

    public CarServiceImpl(final CarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * Find car's list Service.
     *
     * @return car's list Service.
     */

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAllCars() {
        LOG.info("Method findAllCars() started of class {}",
                getClass().getName());

        return carDao.findAllCars();
    }

    /**
     * Find car by id Service.
     *
     * @param id car.
     * @return car by id Service.
     */

    @Override
    @Transactional(readOnly = true)
    public Car findCarById(final Integer id) {
        LOG.info("Method findCarById() with id: {} started of class {}",
                id, getClass().getName());

        try {
            return this.carDao.findCarById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CarNotFoundException(id);
        }
    }

    /**
     * Persist new car.
     *
     * @param car car.
     * @return persisted car id.
     */

    @Override
    @Transactional
    public Integer saveCar(final Car car) {
        LOG.info("Method saveCar() with car: {} started of class {}",
                car, getClass().getName());

        return carDao.saveCar(car);
    }

    /**
     * Update car.
     *
     * @param id car id.
     * @param car car.
     * @return number of updated records in the database.
     */

    @Override
    @Transactional
    public Integer updateCarById(final Integer id,
                                 final Car car) {
        LOG.info("Method updateCarById() with id {}"
                        + " and with car: {} started of class {}",
                id, car, getClass().getName());

        return carDao.updateCarById(id, car);
    }

    /**
     * Delete car.
     *
     * @param id car id.
     * @return number of updated records in the database.
     */

    @Override
    @Transactional
    public Integer deleteCarById(final Integer id) {
        LOG.info("Method deleteCarById() with id:"
                + " {} started of class {}",
                id, getClass().getName());

        return carDao.deleteCarById(id);
    }

    /**
     * Count cars.
     *
     * @return quantity of the cars.
     */

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        LOG.info("Method count() started of class {}",
                getClass().getName());

        return carDao.count();
    }
}
