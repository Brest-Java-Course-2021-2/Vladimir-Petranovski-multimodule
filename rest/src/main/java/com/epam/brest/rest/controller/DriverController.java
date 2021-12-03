package com.epam.brest.rest.controller;

import com.epam.brest.model.Driver;
import com.epam.brest.service_api.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;

@RestController
//@RequestMapping("/drivers")
public class DriverController {

//    private final DriverService driverService;
//
//    public DriverController(DriverService driverService) {
//        this.driverService = driverService;
//    }
//
//    @GetMapping(consumes = "application/json", produces = "application/json")
//    public final ResponseEntity<List<Driver>> findAllDrivers() {
//        log.info("Method findAllDrivers() started of class {}", getClass().getName());
//        List<Driver> driversList= driverService.findAllDrivers();
//        return new ResponseEntity<>(driversList, HttpStatus.OK);
//    }
}
