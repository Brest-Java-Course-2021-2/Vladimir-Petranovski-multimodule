package com.epam.brest.rest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public final ResponseEntity<Driver> findDriverById(@PathVariable("id") Integer id) {
        log.info("Method findDriverById() with id {} started of class {}", id, getClass().getName());
        Driver driver = driverService.findDriverById(id);
        if(driver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    /**
     * Get form for saving driver Dao.
     */

//    @GetMapping(value = "/new-driver", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Void> showFormForAddingDriver(@ModelAttribute Driver driver) {
//        log.info("Method showFormForAddingDriver() with driver {} started of class {}", driver, getClass().getName());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    /**
     * Save driver Dao.
     */

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> saveDriver(@RequestBody Driver driver) {
        log.info("Method findDriverById() with driver {} started of class {}", driver, getClass().getName());
        Integer id = driverService.saveDriver(driver);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
