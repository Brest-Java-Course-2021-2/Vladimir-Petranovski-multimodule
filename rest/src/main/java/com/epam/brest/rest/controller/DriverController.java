package com.epam.brest.rest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public final ResponseEntity<Driver> findDriverById(@PathVariable("id") Integer id) {
        log.info("Method findDriverById() with id {} started of class {}", id, getClass().getName());
        Driver driver = driverService.findDriverById(id);
        if(driver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }
}
