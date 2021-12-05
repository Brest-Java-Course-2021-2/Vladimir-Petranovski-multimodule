package com.epam.brest.service.impl;

import com.epam.brest.dao_api.CarDao;
import com.epam.brest.model.Car;
import com.epam.brest.service_api.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public List<Car> findAllCars() {
        return carDao.findAllCars();
    }

    @Override
    public Car findCarById(Integer id) {
        return carDao.findCarById(id);
    }

    @Override
    public Integer saveCar(Car car) {
        return carDao.saveCar(car);
    }

    @Override
    public Integer updateCarById(Integer id, Car car) {
        return carDao.updateCarById(id, car);
    }

    @Override
    public Integer deleteCarById(Integer id) {
        return carDao.deleteCarById(id);
    }

    @Override
    public Integer count() {
        return carDao.count();
    }
}
