package com.epam.brest.rest.controller;

import com.epam.brest.model.Car;
import com.epam.brest.service_api.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

import static com.epam.brest.logger.ProjectLogger.LOG;

@RestController
@RequestMapping("/cars")
public class CarController {

    /**
     * Field carService.
     */

    private final CarService carService;

    /**
     * Constructor.
     * @param carService carService.
     */

    public CarController(final CarService carService) {
        this.carService = carService;
    }

    /**
     * Goto car's list.
     *
     * @return car's list in json.
     */

    @GetMapping()
    public final Collection<Car> findAllCars() {
        LOG.info("Method findAllCars() started of class {}",
                getClass().getName());
        return carService.findAllCars();
    }

    /**
     * Goto car by id.
     *
     * @param id car.
     * @return car in json.
     */

    @GetMapping(value = "/{id}")
    public final Car findCarById(@PathVariable final Integer id) {
        LOG.info("Method findCarById() with id: {} started of class {}",
                id, getClass().getName());

        return carService.findCarById(id);
    }

    /**
     * Persist car.
     *
     * @param car car.
     * @return 200 ok.
     */

    @PostMapping(consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Integer> saveCar(@RequestBody final Car car) {
        LOG.info("Method saveCar() with car: {} started of class {}",
                car, getClass().getName());

        Integer id = carService.saveCar(car);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Update car.
     *
     * @param car car.
     * @param id car.
     * @return 200 ok.
     */

    @PatchMapping(value = "/{id}", consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<Integer> updateCar(@RequestBody final Car car,
                                             @PathVariable final Integer id) {
        LOG.info("Method updateCar() with car: {} started of class {}",
                car, getClass().getName());

        int result = carService.updateCarById(id, car);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Delete car.
     *
     * @param id car.
     * @return 200 ok.
     */

    @DeleteMapping(value = "/{id}/delete-car",
            produces = "application/json")
    public ResponseEntity<Integer> deleteCar(
            @PathVariable final Integer id) {
        LOG.info("Method updateCar() with id: {} started of class {}",
                id, getClass().getName());

        int result = carService.deleteCarById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
