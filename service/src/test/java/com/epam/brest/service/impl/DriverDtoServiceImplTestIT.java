package com.epam.brest.service.impl;

import com.epam.brest.model.dto.DriverDto;
import com.epam.brest.service_api.DriverDtoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.brest.logger.ProjectLogger.log;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
@Rollback
class DriverDtoServiceImplTestIT {

    @Autowired
    private DriverDtoService driverDtoService;

    @Test
    void shouldFindAllWithCountCars() {
        log.info("Method started: findWithCountCars() of {}", getClass().getName());
        List<DriverDto> drivers = driverDtoService.findAllWithCountCars();
        assertNotNull(drivers);
        assertTrue(drivers.size() > 0);
        log.info("Test passed, list driver Dto equals {}", drivers);
        assertTrue(drivers.get(0).getCountOfCarsAssignedToDriver() > 0);

    }
}