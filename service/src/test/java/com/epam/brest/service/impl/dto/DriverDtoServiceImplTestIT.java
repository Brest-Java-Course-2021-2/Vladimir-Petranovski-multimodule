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
}