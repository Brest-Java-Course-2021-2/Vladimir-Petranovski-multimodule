package com.epam.brest.rest.controller;

import com.epam.brest.model.Car;
import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.epam.brest.logger.ProjectLogger.log;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * Get driver's list Dao.
     *
     * @return Driver's list Dao.
     */

    @GetMapping()
    public Collection<Driver> findAllDrivers() {
        log.info("Method findAllDrivers() started of class {}", getClass().getName());
        return driverService.findAllDrivers();
    }

    /**
     * Get driver Dao by id.
     *
     * @return Driver Dao by id.
     */

    @GetMapping(value = "/{id}")
    public final Driver findDriverById(@PathVariable("id") Integer id) {
        log.info("Method findDriverById() with id {} started of class {}", id, getClass().getName());

        return driverService.findDriverById(id);
    }

    /**
     * Save driver Dao.
     */

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> saveDriver(@RequestBody Driver driver) {
        log.info("Method findDriverById() with driver {} started of class {}", driver, getClass().getName());

        Integer id = driverService.saveDriver(driver);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Update driver Dao by id.
     */

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> updateDriver(@RequestBody Driver driver, @PathVariable Integer id) {
        log.info("Method updateDriver() with id: {} and driver: {} started of class {}", id, driver, getClass().getName());

        Integer quantity = driverService.updateDriverById(id, driver);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }

    /**
     * Delete driver Dao by id.
     */

    @DeleteMapping(value = "{id}/delete-driver", produces = "application/json")
    public ResponseEntity<Integer> deleteDriverById(@PathVariable Integer id) {
        log.info("Method deleteDriverById() with id: {} started of class {}", id, getClass().getName());

        Integer quantity = driverService.deleteDriverById(id);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }
}
