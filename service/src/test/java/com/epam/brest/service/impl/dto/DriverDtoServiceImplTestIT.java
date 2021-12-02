package com.epam.brest.service.impl.dto;

import com.epam.brest.model.dto.DriverDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
@Rollback
class DriverDtoServiceImplTestIT {

    @Autowired
    private DriverDtoServiceImpl driverDtoService;

    @Test
    void findAllDriverWithCountCars() {
        log.info("Method started: findAllDriverWithCountCars() of {}", getClass().getName());
        List<DriverDto> drivers = driverDtoService.findAllDriverWithCountCars();
        assertNotNull(drivers);
        assertTrue(drivers.size() > 0);
        log.info("Test passed, list driver Dto equals {}", drivers);
        assertTrue(drivers.get(0).getCountOfCarsAssignedToDriver() > 0);
    }

    @Test
    void chooseDriverOnDateRange() {
        log.info("Method started: chooseDriverOnDateRange() of {}", getClass().getName());
        String fromDate = "1990-01-02T10:10:10.002Z";
        String toDate = "2021-01-02T10:10:10.002Z";
        List<DriverDto> drivers = driverDtoService.chooseDriverOnDateRange(fromDate, toDate);
        assertNotNull(drivers);
        log.info("List of driver Dto was created {}", drivers);
    }
}