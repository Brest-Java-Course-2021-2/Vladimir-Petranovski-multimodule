package com.epam.brest.dao;

import com.epam.brest.dao_api.CarDao;
import com.epam.brest.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-config.xml"})
@Transactional
@Rollback
class CarDaoJdbcImplTestIT {

    @Autowired
    private CarDaoJdbcImpl carDaoJdbc;

    @Test
    void findAllCars() {
        log.info("Method started: findAllCars() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        assertNotNull(carDaoJdbc.findAllCars());
        log.info("{}", carDaoJdbc.findAllCars());
    }

    @Test
    void findCarById() {
        log.info("Method started: findCarById() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        List<Car> cars = carDaoJdbc.findAllCars();
        if(cars.size() == 0) {
            carDaoJdbc.saveCar(new Car("LUAZ", 3));
            cars = carDaoJdbc.findAllCars();
        }
        Car carSrc = cars.get(0);
        Car carDst = carDaoJdbc.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        log.info("Car's model first from list: {} equals car's model after finding: {}", carSrc.getCarModel(), carDst.getCarModel());
    }

    @Test
    void saveCar() {
        log.info("Method started: saveCar() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        int countCarBeforeSave = carDaoJdbc.count();
        Car car = new Car("GAZON", 3);
        carDaoJdbc.saveCar(car);
        int countCarAfterSave = carDaoJdbc.count();
        assertEquals(countCarBeforeSave, countCarAfterSave - 1);
        log.info("Record's count before save():{} equals record's count after save minus one: {}", countCarBeforeSave, countCarAfterSave - 1);

    }

    @Test
    void updateCarById() {
        log.info("Method started: updateCarById() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        List<Car> cars = carDaoJdbc.findAllCars();
        if(cars.size() == 0) {
            carDaoJdbc.saveCar(new Car("LUAZ", 3));
            cars = carDaoJdbc.findAllCars();
        }
        Car carSrc = cars.get(0);
        carSrc.setCarModel(carSrc.getCarModel() + "_TEST");
        carDaoJdbc.updateCarById(carSrc.getCarId(), carSrc);

        Car carDst = carDaoJdbc.findCarById(carSrc.getCarId());
        assertEquals(carSrc.getCarModel(), carDst.getCarModel());
        log.info("Driver's name first from list: {} equals driver's name after updating: {}", carSrc.getCarModel(), carDst.getCarModel());
    }

    @Test
    void deleteCarById() {
        log.info("Method started: deleteCarById() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        carDaoJdbc.saveCar(new Car("LUAZ", 3));
        List<Car> cars = carDaoJdbc.findAllCars();

        Car carSrc = carDaoJdbc.findCarById(cars.size() - 1);
        carDaoJdbc.deleteCarById(carSrc.getCarId());
        assertEquals(cars.size() -1, carDaoJdbc.findAllCars().size());
        log.info("First car's size list minus one: {} equals car's size list after deleting {}", cars.size() -1, carDaoJdbc.findAllCars().size());
    }

    @Test
    void checkCount() {
        log.info("Method started: checkCount() of {}", getClass().getName());
        assertNotNull(carDaoJdbc);
        Integer quantity = carDaoJdbc.count();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        log.info("Quantity equals {}", quantity);
        assertEquals(carDaoJdbc.findAllCars().size(), quantity);
        log.info("Size of collection {} equals record's quantity {}", carDaoJdbc.findAllCars().size(), quantity);
    }
}