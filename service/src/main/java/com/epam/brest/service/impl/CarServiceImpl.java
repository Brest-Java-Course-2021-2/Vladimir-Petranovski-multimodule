package com.epam.brest.service.impl;

import com.epam.brest.dao_api.CarDao;
import com.epam.brest.model.Car;
import com.epam.brest.service.exception.CarNotFoundException;
import com.epam.brest.service_api.CarService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;

@Service
public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAllCars() {
        log.info("Method findAllCars() started of class {}", getClass().getName());

        return carDao.findAllCars();
    }

    @Override
    @Transactional(readOnly = true)
    public Car findCarById(Integer id) {
        log.info("Method findCarById() with id: {} started of class {}", id, getClass().getName());

        try {
            return this.carDao.findCarById(id);
        } catch (EmptyResultDataAccessException e) {
            throw  new CarNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public Integer saveCar(Car car) {
        log.info("Method saveCar() with car: {} started of class {}", car, getClass().getName());

        return carDao.saveCar(car);
    }

    @Override
    @Transactional
    public Integer updateCarById(Integer id, Car car) {
        log.info("Method updateCarById() with id {} and with car: {} started of class {}", id, car, getClass().getName());

        return carDao.updateCarById(id, car);
    }

    @Override
    @Transactional
    public Integer deleteCarById(Integer id) {
        log.info("Method deleteCarById() with id: {} started of class {}", id, getClass().getName());

        return carDao.deleteCarById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        log.info("Method count() started of class {}", getClass().getName());

        return carDao.count();
    }
}
