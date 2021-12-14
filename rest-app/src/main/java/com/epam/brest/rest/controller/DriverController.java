package com.epam.brest.rest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

import static com.epam.brest.logger.ProjectLogger.LOG;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    /**
     * Field driverService.
     */

    private final DriverService driverService;

    /**
     * Constructor.
     *
     * @param  driverService DriverService.
     */

    public DriverController(
            final DriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * Goto driver's list.
     *
     * @return driver's list in json.
     */

    @GetMapping()
    public Collection<Driver> findAllDrivers() {
        LOG.info("Method findAllDrivers() started of class {}",
                getClass().getName());
        return driverService.findAllDrivers();
    }

    /**
     * Goto driver by id.
     *
     * @param id driver.
     * @return driver in json.
     */

    @GetMapping(value = "/{id}")
    public final Driver findDriverById(@PathVariable("id") final Integer id) {
        LOG.info("Method findDriverById() with id {} started of class {}",
                id, getClass().getName());

        return driverService.findDriverById(id);
    }

    /**
     * Persist driver.
     *
     * @param driver driver.
     * @return 200 ok.
     */

    @PostMapping(consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Integer> saveDriver(
            @RequestBody final Driver driver) {
        LOG.info("Method findDriverById() with driver {} started of class {}",
                driver, getClass().getName());

        Integer id = driverService.saveDriver(driver);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Update driver.
     *
     * @param driver driver.
     * @param id driver.
     * @return 200 ok.
     */

    @PatchMapping(value = "/{id}", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Integer> updateDriver(
            @RequestBody final Driver driver,
            @PathVariable final Integer id) {
        LOG.info("Method updateDriver() with id: {}"
                        + " and driver: {} started of class {}",
                id, driver, getClass().getName());

        Integer quantity = driverService.updateDriverById(id, driver);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }

    /**
     * Delete driver.
     *
     * @param id driver.
     * @return 200 ok.
     */

    @DeleteMapping(value = "{id}/delete-driver",
            produces = "application/json")
    public ResponseEntity<Integer> deleteDriverById(
            @PathVariable final Integer id) {
        LOG.info("Method deleteDriverById() with id: {} started of class {}",
                id, getClass().getName());

        Integer quantity = driverService.deleteDriverById(id);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }
}
