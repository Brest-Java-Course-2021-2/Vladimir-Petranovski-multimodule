package com.epam.brest.rest.controller.dto;

import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

import static com.epam.brest.logger.ProjectLogger.LOG;

@RestController
@RequestMapping("/drivers_dto")
public class DriverDtoController {

    /**
     * Field driverDtoService.
     */

    private final DriverDtoService driverDtoService;

    /**
     * Constructor.
     *
     * @param enterDriverDtoService driverDtoService.
     */

    public DriverDtoController(
            @Qualifier("driverDtoServiceImpl")
            final DriverDtoService enterDriverDtoService) {
        this.driverDtoService = enterDriverDtoService;
    }

    /**
     * Fid driver's list Dto.
     *
     * @return Driver Dto collection in json format.
     */

    @GetMapping()
    public final Collection<DriverDto> findAllDriversWithCountCars() {
        LOG.info("Method findAllDriversWithCountCars() started of class {}",
                getClass().getName());
        return driverDtoService.findAllDriverWithCountCars();
    }

    /**
     * Fid driver's list Dto from date to date.
     *
     * @return Driver Dto collection in json format.
     */

    @PostMapping(value = "/drivers-range", consumes = "application/json",
            produces = "application/json")
    public Collection<DriverDto> showDriversListOnRange(@RequestBody DriverDto driverDto) {
        LOG.info("Method showDriversListOnRange() started of class {}",
                getClass().getName());
        return driverDtoService.chooseDriverOnDateRange(driverDto.getFromDateChoose(), driverDto.getToDateChoose());
    }
}
