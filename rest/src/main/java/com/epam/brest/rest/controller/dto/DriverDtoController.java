package com.epam.brest.rest.controller.dto;

import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.epam.brest.logger.ProjectLogger.log;

@RestController
@RequestMapping("/drivers")
public class DriverDtoController {

    private final DriverDtoService driverDtoService;

    public DriverDtoController(DriverDtoService driverDtoService) {
        this.driverDtoService = driverDtoService;
    }

    @GetMapping()
    public final Collection<DriverDto> findAllDriversWithCountCars() {
        log.info("Method findAllDriversWithCountCars() started of class {}", getClass().getName());
        return driverDtoService.findAllDriverWithCountCars();
    }
}
