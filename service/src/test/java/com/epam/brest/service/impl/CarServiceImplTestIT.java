package com.epam.brest.service.impl;

import com.epam.brest.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
@Rollback
class CarServiceImplTestIT {

    public static final Logger LOG = LogManager.getLogger(CarServiceImplTestIT.class);

    @Autowired
    CarServiceImpl carService;

    @Test
    void findAllCars() {
        LOG.info("Method started: findAllCars() of {}", getClass().getName());
        assertNotNull(carService);
        assertNotNull(carService.findAllCars());
        LOG.info("{}", carService.findAllCars());
    }

    @Test
    void findCarById() {
        LOG.info("Method started: findCarById() of {}", getClass().getName());
        assertNotNull(carService);
        List<Car> cars = carService.findAllCars();
        if(cars.size() == 0) {
            carService.saveCar(new Car("ZILOK", 2));
            cars = carService.findAllCars();
        }
        Car carSrc = cars.get(0);
        Car carDst = carService.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        LOG.info("Car's name first from list: {} equals car's name after finding by id {}: {}",
                carSrc.getCarModel(), carSrc.getCarId(), carDst.getCarModel());
    }

    @Test
    void saveCar() {
        LOG.info("Method started: saveCar() of {}", getClass().getName());
        assertNotNull(carService);
        Integer countCarBeforeSave = carService.count();
        Integer countCarAfterSave = carService.saveCar(new Car("LADA", 1));
        assertNotNull(countCarAfterSave);
        assertEquals(countCarBeforeSave, carService.count() - 1);
        LOG.info("Size driver's list before save():{} equals after save minus one {}", countCarBeforeSave, carService.count() - 1);
    }

    @Test
    void updateCarById() {
        LOG.info("Method started: updateCarById() of {}", getClass().getName());
        assertNotNull(carService);
        List<Car> cars = carService.findAllCars();
        if(cars.size() == 0) {
            carService.saveCar(new Car("AUDI", 1));
            cars = carService.findAllCars();
        }
        Car carSrc = cars.get(0);
        carSrc.setCarModel(carSrc.getCarModel() + "_TEST");
        carService.updateCarById(carSrc.getCarId(), carSrc);

        Car carDst = carService.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        LOG.info("Car's name first from list: {} equals car's name after updating: {}", carSrc.getCarModel(), carDst.getCarModel());
    }

    @Test
    void deleteCarById() {
        LOG.info("Method started: deleteCarById() of {}", getClass().getName());
        assertNotNull(carService);
        carService.saveCar(new Car("MUSTANG", 1));
        Integer countRecordsBeforeDelete = carService.count();

        List<Car> cars = carService.findAllCars();
        Car carSrc = cars.get(cars.size() - 1);
        carService.deleteCarById(carSrc.getCarId());
        assertEquals(countRecordsBeforeDelete - 1, carService.findAllCars().size());
        LOG.info("Count records before deleting minus one: {} equals car's size list after deleting {}", countRecordsBeforeDelete - 1, carService.findAllCars().size());
    }

    @Test
    void count() {
        LOG.info("Method started: count() of {}", getClass().getName());
        assertNotNull(carService);
        List<Car> cars = carService.findAllCars();
        assertEquals(cars.size(), carService.count());
        LOG.info("Quantity of records {} equals size of car's list {}", carService.count(), cars.size());
    }
}