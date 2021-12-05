package com.epam.brest.rest.controller;

import com.epam.brest.model.Car;
import com.epam.brest.service_api.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.epam.brest.logger.ProjectLogger.log;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Get car's list Dao.
     *
     * @return car list Dao.
     */

    @GetMapping(consumes = "application/app", produces = "application/app")
    public final Collection<Car> findAllCars() {
        log.info("Method findAllCars() started of class {}", getClass().getName());
        return carService.findAllCars();
    }

    /**
     * Save car Dao.
     *
     */

    @PostMapping(consumes = "application/app", produces = "application/app")
    public ResponseEntity<Void> saveCar(@RequestBody Car car) {
        log.info("Method saveCar() with car: {} started of class {}", car, getClass().getName());
        carService.saveCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}