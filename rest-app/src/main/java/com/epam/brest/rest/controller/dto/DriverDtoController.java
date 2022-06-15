package com.epam.brest.rest.controller.dto;

import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.dto.DriverDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/drivers_dto")
public class DriverDtoController {

    public static final Logger LOG = LogManager.getLogger(DriverDtoController.class);

    /**
     * Field driverDtoService.
     */

    private final DriverDtoService driverDtoService;

    /**
     * Constructor.
     *
     * @param driverDtoService driverDtoService.
     */

    public DriverDtoController(
            final DriverDtoService driverDtoService) {
        this.driverDtoService = driverDtoService;
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

    @GetMapping("/drivers-range")
    public Collection<DriverDto> showDriversListOnRange(
            @ModelAttribute final DriverDto driverDto) {
        LOG.info("Method showDriversListOnRange() started of class {} - {}",
                driverDto.getFromDateChoose(), driverDto.getToDateChoose());
        return driverDtoService.chooseDriverOnDateRange(driverDto.getFromDateChoose(),
                driverDto.getToDateChoose());
    }
}
